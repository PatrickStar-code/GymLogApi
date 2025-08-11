package com.Gymlog.Seeders;

import com.Gymlog.Adapters.LocalDateTimeAdapter;
import com.Gymlog.Entity.FoodEntity;
import com.Gymlog.Entity.UserEntity;
import com.Gymlog.Repository.FoodRepository;
import com.Gymlog.Repository.UserRepository;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
 class DatabaseSeeder implements CommandLineRunner {

    private final FoodRepository foodRepository;
    private final UserRepository userRepository;

    // Crie o Gson com o adapter registrado aqui
    Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();

    @Override
    public void run(String... args) throws Exception {
        if(foodRepository.count() == 0) {
            try (InputStreamReader reader = new InputStreamReader(
                    getClass().getResourceAsStream("/Json/food.json"))) {


                if (reader == null) {
                    throw new FileNotFoundException("❌ Arquivo /Json/food.json não encontrado no classpath!");
                }


                JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

                if (!jsonObject.has("food")) {
                    throw new IllegalArgumentException("❌ O JSON não contém a chave 'food'.");
                }

                Type listType = new TypeToken<ArrayList<FoodEntity>>() {}.getType();
                ArrayList<FoodEntity> foods = gson.fromJson(jsonObject.getAsJsonArray("food"), listType);

                if (foods == null || foods.isEmpty()) {
                    throw new IllegalArgumentException("❌ Lista de alimentos está vazia ou nula.");
                }

                for (FoodEntity food : foods) {
                    if (food == null) {
                        throw new IllegalArgumentException("❌ Encontrado objeto null na lista de alimentos.");
                    }
                }

                foodRepository.saveAll(foods);
                System.out.println("✅ Food table seeded!");
            }
        }

        if (userRepository.count() == 0) {
            try (InputStreamReader reader = new InputStreamReader(
                    getClass().getResourceAsStream("/Json/users.json"), StandardCharsets.UTF_8)) {

                if (reader == null) {
                    throw new FileNotFoundException("❌ Arquivo /Json/users.json não encontrado no classpath!");
                }

                JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

                if (!jsonObject.has("users")) {
                    throw new IllegalArgumentException("❌ O JSON não contém a chave 'users'.");
                }

                Type listType = new TypeToken<ArrayList<UserEntity>>() {}.getType();
                ArrayList<UserEntity> users = gson.fromJson(jsonObject.getAsJsonArray("users"), listType);

                if (users == null || users.isEmpty()) {
                    throw new IllegalArgumentException("❌ Lista de usuários está vazia ou nula.");
                }

                for (UserEntity user : users) {
                    if (user == null) {
                        throw new IllegalArgumentException("❌ Encontrado objeto null na lista de usuários.");
                    }
                    if (user.getUsername() == null || user.getPassword() == null) {
                        throw new IllegalArgumentException("❌ Usuário com campos obrigatórios nulos: " + user);
                    }
                }

                userRepository.saveAll(users);
                System.out.println("✅ User table seeded!");
            }
        }
    }
}
