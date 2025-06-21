package com.Gymlog.Controllers.Response;

import lombok.Builder;

@Builder
public record WorkoutPlanResponse(Long id,
                                  String name,
                                  String imageUrl,
                                  Long userId
                            ) {
}
