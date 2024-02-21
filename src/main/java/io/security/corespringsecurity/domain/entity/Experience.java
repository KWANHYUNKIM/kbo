package io.security.corespringsecurity.domain.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@AllArgsConstructor
@Entity
@ToString(exclude = {"account"})
public class Experience
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int xp;
    private int level;

    @OneToOne(mappedBy = "experience")
    private Account account;

    private static final int[] XP_REQUIRED = new int[1000];

    static {
        int xp = 800;
        for (int i = 0; i < XP_REQUIRED.length; i++) {
            XP_REQUIRED[i] = xp;
            xp += 200; // 200씩 증가
        }
    }
    public int[] getXpRequired() {
        return XP_REQUIRED;
    }

    public Experience() {
        this.xp = 0; // 초기 XP는 0으로 설정
        this.level = 0; // 초기 레벨은 0으로 설정
    }

    public void setAccount(Account account) {
        this.account = account;
        account.setExperience(this);
    }
}
