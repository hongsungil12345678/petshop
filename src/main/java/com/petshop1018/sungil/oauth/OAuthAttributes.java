package com.petshop1018.sungil.oauth;

import com.petshop1018.sungil.domain.Member;
import com.petshop1018.sungil.domain.Role;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String username;
    private String nickname;
    private String email;
    private Role role;

    public static OAuthAttributes of(String registrationId, String userNameAttributeName,
                                     Map<String, Object> attributes) {
        /* 구글인지 네이버인지 카카오인지 구분하기 위한 메소드 (ofNaver, ofKaKao) */
        if ("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        }

        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName,
                                            Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .username((String) attributes.get("email"))
                .email((String) attributes.get("email"))
                .nickname((String) attributes.get("name"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        /* JSON형태이기 때문에 Map을 통해 데이터를 가져온다. */
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        log.info("naver response : " + response);

        return OAuthAttributes.builder()
                .username((String) response.get("email"))
                .email((String) response.get("email"))
                .nickname((String) response.get("nickname"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public Member toEntity() {
        // 이메일이 "indiahong1@gmail.com"이면 ADMIN 역할을 부여하고, 그렇지 않으면 SOCIAL 역할 부여
        Role assignedRole = email.equals("indiahong1@gmail.com") ? Role.ADMIN : Role.SOCIAL;

        return Member.builder()
                .username(email)
                .email(email)
                .nickname(nickname)
                .password(UUID.randomUUID().toString())
                .role(assignedRole)
                .build();
    }
}
