    package org.zerock.apiserver.service;

    import lombok.RequiredArgsConstructor;
    import lombok.extern.java.Log;
    import lombok.extern.log4j.Log4j2;
    import org.apache.coyote.Response;
    import org.springframework.http.HttpEntity;
    import org.springframework.http.HttpHeaders;
    import org.springframework.http.HttpMethod;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Service;
    import org.springframework.web.client.RestTemplate;
    import org.springframework.web.util.UriComponents;
    import org.springframework.web.util.UriComponentsBuilder;
    import org.zerock.apiserver.domain.Member;
    import org.zerock.apiserver.domain.MemberRole;
    import org.zerock.apiserver.dto.MemberDTO;
    import org.zerock.apiserver.dto.MemberModifyDTO;
    import org.zerock.apiserver.repository.MemberRepository;

    import java.util.LinkedHashMap;
    import java.util.Optional;


    @Service
    @RequiredArgsConstructor
    @Log4j2
    public class MemberServiceImpl implements MemberService{

        private final MemberRepository memberRepository;

        private final PasswordEncoder passwordEncoder;


        @Override
        public void modifyMember(MemberModifyDTO memberModifyDTO) {
            Optional<Member> result = memberRepository.findById(memberModifyDTO.getEmail());

            Member member = result.orElseThrow();

            member.changeNickname(memberModifyDTO.getNickname());
            member.changeSocial(false);
            member.changePw(passwordEncoder.encode(memberModifyDTO.getPw()));

            memberRepository.save(member);

        }

        @Override
        public MemberDTO getKakaoMember(String accessToken) {
        //aceesstoken을 이용해서 사용자 정보 가져오기
            //기존 db에 회원 정보가 있는경우 / 없는 경우

            String nickname = getEmailFromKakaoAccessToken(accessToken);

            Optional<Member> result = memberRepository.findById(nickname);
            if(result.isPresent()){

                MemberDTO memberDTO = entityToDTO(result.get());
                log.info("existed------------------------" + memberDTO);
                return memberDTO;
            }
            Member socialMember = makeSocialMember(nickname);
            memberRepository.save(socialMember);

            MemberDTO memberDTO = entityToDTO(socialMember);
            return memberDTO;
        }

        private Member makeSocialMember(String email){
            String tempPassword = makeTempPassword();
            log.info("tempPassword : " + tempPassword);

            Member member = Member.builder()
                    .email(email)
                    .pw(passwordEncoder.encode(tempPassword))
                    .nickname("Social Member")
                    .social(true)   //소셜로 로그인했으니까
                    .build();

            member.addRole(MemberRole.USER);
            return member;
        }

        private String getEmailFromKakaoAccessToken(String accessToken){
            String kakaoGetUserURL = "https://kapi.kakao.com/v2/user/me";

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + accessToken);
            headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

            HttpEntity<String> entity = new HttpEntity<>(headers);
            UriComponents uriBuilder = UriComponentsBuilder.fromHttpUrl(kakaoGetUserURL).build();

            ResponseEntity<LinkedHashMap> response =
                    restTemplate.exchange(uriBuilder.toUri(), HttpMethod.GET, entity, LinkedHashMap.class);

            log.info(response);

            LinkedHashMap<String, LinkedHashMap> bodyMap = response.getBody();

            LinkedHashMap<String, String> kakaoAccount = bodyMap.get("properties");
            log.info("kakaoAcount : " + kakaoAccount);

             String nickname = kakaoAccount.get("nickname");

             log.info("nickname: " + nickname);

             return nickname;
        }

        private String makeTempPassword() {

            StringBuffer buffer = new StringBuffer();
            for(int i = 0; i < 10; i++){
                buffer.append((char) ((int)(Math.random()*55) + 65));
            }
            return buffer.toString();
        }

    }
