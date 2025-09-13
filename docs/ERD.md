# 타자나라 API ERD

## Mermaid ER Diagram

```mermaid
erDiagram
    TB_USER ||--o{ TB_USER_ROLE: "assigns"
    TB_ROLE ||--o{ TB_USER_ROLE: "assigned"
    TB_USER ||--|| TB_SETTING: "has"
    TB_USER ||..o{ TB_SCORE: "writes"
    TB_SONG ||..o{ TB_SCORE: "has"

    TB_USER {
        varchar(36) id PK
    }

    TB_ROLE {
        varchar(36) id PK
    }

    TB_USER_ROLE {
        varchar(36) user_id PK, FK
        varchar(36) role_id PK, FK
    }

    TB_SETTING {
        varchar(36) user_id PK, FK
    }

    TB_SONG {
        varchar(36) id PK
    }

    TB_SCORE {
        varchar(36) id PK
        varchar(36) user_id FK
        varchar(36) song_id FK
    }
```

## 관계 요약

### tb_user

- 0 .. n `tb_user_role`
- 0 .. 1 `tb_setting`

### tb_user_role

- 0 .. n `tb_user`
- 0 .. n `tb_role`

### tb_role

- 0 .. n `tb_user_role`

### tb_setting

- 1 .. 1 `tb_user`

### tb_song

- 0 .. n `tb_score`

### tb_score

- 0 .. n `tb_song`