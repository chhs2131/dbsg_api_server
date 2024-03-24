package kr.co.dbsg.api.api.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import kr.co.dbsg.api.api.auth.entity.LoginTypeEntity;
import kr.co.dbsg.api.api.auth.entity.MemberPermissionEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "member_permission_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private MemberPermissionEntity memberPermission;

    @ManyToOne
    @JoinColumn(name = "login_type_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private LoginTypeEntity loginType;

    @Column(name = "origin_id", nullable = false)
    private String originId;  // OAuth 기관에서 관리하는 고유 번호 (이름이 좀...)

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public MemberEntity(String name, MemberPermissionEntity memberPermission, LoginTypeEntity loginType, String originId) {
        this.name = name;
        this.memberPermission = memberPermission;
        this.loginType = loginType;
        this.originId =originId;

        final LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }
}
