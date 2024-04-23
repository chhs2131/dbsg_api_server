package kr.co.dbsg.api.global.util;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomNameMaker {
    private static final List<String> COMPANY = Arrays.asList(
        "삼성", "현대", "LG", "SK", "롯데", "CJ", "카카오", "네이버", "쿠팡", "쏘카",
        "GS", "한화", "동부", "포스코", "두산", "롯데그룹", "신세계그룹", "CJ그룹", "SK그룹", "현대그룹",
        "KT", "LG디스플레이", "삼성SDI", "삼성전자반도체", "SK하이닉스", "셀트리온", "유한양행", "현대제철", "포스코케미칼", "동국제강",
        "KB금융그룹", "신한금융그룹", "우리금융그룹", "하나금융그룹", "NH농협금융그룹", "삼성생명", "현대카드", "KB손해보험", "현대캐피탈", "삼성증권",
        "아모레퍼시픽", "LG생활건강", "CJ헬스케어", "롯데제과", "빙그레", "Orion", "해태제과", "삼양식품", "농심", "CJ대한제당",
        "현대위아", "동서파워", "삼성물산", "두산중공업", "코웨이", "LS엠트론", "웅진그룹", "효성그룹", "대우조선해양", "삼성중공업",
        "현대건설", "삼성건설", "SK건설", "롯데건설", "한화건설", "대우건설", "현대건설", "롯데건설", "한화건설", "대우건설",
        "하이닉스", "씨젠", "엠디엠", "엘앤에프", "DB손해보험", "KB손해보험", "현대캐피탈", "삼성증권", "KB증권", "NH투자증권",
        "유니레버코리아", "P&G코리아", "삼성물산", "CJ프레시웨이", "롯데마트", "이마트", "신세계백화점", "현대백화점", "롯데백화점", "CJ홈쇼핑",
        "망고플레이트", "페이스캠", "워플리", "뱅크샐러리", "토스", "카카오뱅크", "써클", "웨얼플러스", "컬투스",
        "플레이어스", "굿닥", "몬스터트럭", "카카오브레인", "웨이브", "얀olja", "망고플레이트", "페이스캠", "워플리", "뱅크샐러리",
        "쿠팡이츠", "배달의민족", "요기요", "티몬", "당근마켓", "번개장터", "카카오마켓", "당근마켓", "옥션", "G마켓",
        "페이스북", "인스타그램", "유튜브", "틱톡", "트위터", "넷플릭스", "스포티파이", "디즈니+", "애플뮤직", "카카오M",
        "위메프", "반디", "숨", "벅스", "멜론", "플로", "진진", "왓챠", "티빙", "쿠팡플레이",
        "네이버페이", "카카오페이", "토스", "라인페이", "뱅크월렛", "삼성페이", "현대페이", "KB페이", "우리페이", "NH페이",
        "토요타", "소니", "혼다", "닛산", "파나소닉", "샤프", "미쓰비시", "SoftBank", "Uniqlo", "Rakuten",
        "아마존", "애플", "마이크로소프트", "테슬라", "페이스북", "알파벳 (구글)", "버라이즌", "JP모건 체이스", "뱅크 오브 아메리카", "Berkshire Hathaway",
        "시멘스", "BMW", "메르세데스-벤츠", "볼보", "아디다스", "로레알", "에어비앤비", "스페인 Santander", "BNP Paribas", "LVMH"
    );

    private static final List<String> ANIMALS = Arrays.asList(
        "개", "고양이", "토끼", "소", "말", "돼지", "사슴", "곰", "판다", "고래", "돌고래", "코끼리", "원숭이", "호랑이", "사자", "표범", "바다표범", "하마",
        "기린", "캥거루", "코알라", "너구리", "족제비", "담비", "햄스터", "쥐", "다람쥐", "고슴도치", "박쥐", "닭", "오리", "거위", "백조", "독수리", "매",
        "갈매기", "참새", "까마귀", "앵무새", "병아리", "타조", "쿠두루미", "딱따구리", "꾀꼬리", "후두둑", "황새", "갈매기", "도마뱀", "뱀", "거북이", "악어",
        "두꺼비", "개구리", "민달팽이", "해파리", "별해파리", "미더덕", "거미", "게", "새우", "낙지", "문어", "바다별", "물고기", "산호초", "조개",
        "잉어", "붕어", "참치", "고등어", "연어", "광어", "구피", "베타", "플라잉피쉬", "상어", "고래", "돌고래", "연어", "삼치", "꽁치", "광어", "민태", "옥돔",
        "갈치", "조개", "낙지", "문어", "새우", "게", "해파리"
    );

    public static String generate() {
        Random random = new Random();

        // 회사 목록에서 랜덤 색 선택
        int colorIndex = random.nextInt(COMPANY.size());
        String company = COMPANY.get(colorIndex);

        // 동물 목록에서 랜덤 동물 선택
        int animalIndex = random.nextInt(ANIMALS.size());
        String animal = ANIMALS.get(animalIndex);

        // 랜덤 접미어
        int randomNumber = random.nextInt() % 10000;
        randomNumber = randomNumber < 0 ? randomNumber * -1 : randomNumber;

        // 랜덤 닉네임 생성
        String nickname = company + animal + randomNumber;

        return nickname;
    }
}
