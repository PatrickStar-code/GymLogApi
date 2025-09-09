package com.Gymlog.Validator;

import com.Gymlog.Entity.ProgressLogEntity;

public class ProgressLogValidador {
    public static void verifyErrorsProgressLog(ProgressLogEntity progressLogEntity) {
        if(progressLogEntity.getDate() == null ){
            throw new IllegalArgumentException("Data nao pode ser nula!");
        }

        if(progressLogEntity.getWeight() == 0){
            throw new IllegalArgumentException("Peso nao pode ser zero!");
        }

        if(progressLogEntity.getBodyFat() == 0){
            throw new IllegalArgumentException("Gordura corporal nao pode ser zero!");
        }

        if(progressLogEntity.getHip() == 0){
            throw new IllegalArgumentException("Cintura nao pode ser zero!");
        }

        if(progressLogEntity.getChest() == 0){
            throw new IllegalArgumentException("Pecho nao pode ser zero!");
        }

        if(progressLogEntity.getArmsLeft() == 0){
            throw new IllegalArgumentException("Biceps esquerdo nao pode ser zero!");
        }

        if(progressLogEntity.getArmsRight() == 0){
            throw new IllegalArgumentException("Biceps direito nao pode ser zero!");
        }

        if(progressLogEntity.getThighLeft() == 0){
            throw new IllegalArgumentException("Quadril esquerdo nao pode ser zero!");
        }

        if(progressLogEntity.getThighRight() == 0){
            throw new IllegalArgumentException("Quadril direito nao pode ser zero!");
        }

        if(progressLogEntity.getLeftCalf() == 0){
            throw new IllegalArgumentException("Panturrilha esquerda nao pode ser zero!");
        }

        if(progressLogEntity.getRightCalf() == 0){
            throw new IllegalArgumentException("Panturrilha direita nao pode ser zero!");
        }

        if(progressLogEntity.getWaist() == 0){
            throw new IllegalArgumentException("Cintura nao pode ser zero!");
        }

        if(progressLogEntity.getResponsible() == null || progressLogEntity.getResponsible() == ""){
            throw new IllegalArgumentException("Responsavel nao pode ser nulo!");
        }
    }
}
