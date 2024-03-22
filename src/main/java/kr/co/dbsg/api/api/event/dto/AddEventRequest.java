package kr.co.dbsg.api.api.event.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public record AddEventRequest(
    @Min(0)
    Long stockId,
    @Size(min = 1)
    String eventType
) {
}
