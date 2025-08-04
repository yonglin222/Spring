package com.dw.gameshop_mybatis.dto;

import com.dw.gameshop_mybatis.enums.GameGenre;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GameDTO {
    private long id;
    private String title;
    private GameGenre genre;
    private int price;
    private String imageUrl;
    private String text;
}
/* DTO 를 사용해야 하는 이유
- API 명세와 내부 데이터베이스 구조를 분리하여 서로에게 영향을 주지 않도록 하는 것
1. API 의 안정성 및 유연성 증가
내부 구조 변경으로부터 자유로움: 데이터베이스 테이블의 컬럼 이름이나 타입이 변경되어도 DTO 만 그대로 유지하면,
클라이언트에 보내는 API 명세는 전혀 영향을 받지 않음.
클라이언트는 서버 내부에서 어떤 일이 일어났는지 알 필요 없이 기존과 동일하게 작동할 수 있음.
독립적인 개발: 프론트엔드 개발자는 확정된 DTO 명세만 보고 개발을 진행할 수 있고, 백엔드 개발자는 데이터베이스
구조를 자유롭게 개선할 수 있음. 서로의 작업에 미치는 영향이 최소화됨.

2. 필요한 데이터만 선택적 노출 (보안 강화)
모델(Entity) 클래스에는 클라이언트가 절대 알아서는 안 될 민감한 정보가 포함될 수 있음.
User 모델에는 암호화된 비밀번호가 있지만, UserDTO 에는 이 필드를 아예 포함하지 않음으로써 비밀번호 정보가
실수로라도 외부에 노출되는 것을 원천적으로 차단할 수 있음.
불필요한 정보 제외: 관리자만 봐야 하는 내부 플래그나, 시스템 운영에만 필요한 데이터들을 제외하고 클라이언트에게
꼭 필요한 정보만 추려서 보낼 수 있음.

3. 유효성 검사(Validation) 역할 분리
클라이언트로부터 데이터를 받을 때(Request), 각 상황에 맞는 유효성 검사를 DTO 계층에서 수행할 수 있음.
*/