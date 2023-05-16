package wayc.backend.common.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import wayc.backend.common.domain.BaseStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedDate
    private String createdAt;

    @LastModifiedDate
    private String updatedAt;

    @Enumerated(EnumType.STRING)
    private BaseStatus status;

    protected BaseEntity() {
        this.status = BaseStatus.ACTIVE;
    }

    protected void delete(){
        this.status = BaseStatus.INACTIVE;
    }
}