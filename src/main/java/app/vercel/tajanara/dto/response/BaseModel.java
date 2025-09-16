package app.vercel.tajanara.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@ToString
@EqualsAndHashCode
@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor
public abstract class BaseModel implements Serializable {

    @Schema(description = "식별자(UUID)", example = "8f14e45f-ea9d-4b1c-a3a4-12c4b2a9c001")
    private String id;

    @Schema(description = "생성자 계정명", example = "admin")
    private String createdBy;

    @Schema(description = "생성 일시", example = "2025-01-01T09:00:00")
    private LocalDateTime createdDate;

    @Schema(description = "최종 수정자 계정명", example = "editor")
    private String lastModifiedBy;

    @Schema(description = "최종 수정 일시", example = "2025-01-02T10:30:00")
    private LocalDateTime lastModifiedDate;

}
