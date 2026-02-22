package a0219.enum_example;

public class LottoRankExample {
    public static void main(String[] args) {
        // 일치 개수로 등수 찾기
        int matchCount = 4;
        LottoRank rank = LottoRank.fromMatchCount(matchCount);
        
        System.out.println("일치 개수: " + matchCount);
        System.out.println("등수: " + rank.getName());
        System.out.println("상금: " + rank.getPrize() + "원");
        System.out.println("당첨 여부: " + (rank.isWinner() ? "당첨" : "낙첨"));
        
        System.out.println("\n=== 모든 등수 정보 ===");
        for (LottoRank r : LottoRank.values()) {
            System.out.println(r.getName() + " - 일치: " + r.getMatchCount() + 
                             "개, 상금: " + r.getPrize() + "원");
        }
        
        // switch문 사용 예제
        System.out.println("\n=== 등수별 메시지 ===");
        switch (rank) {
            case FIRST:
                System.out.println("축하합니다! 1등 당첨!");
                break;
            case SECOND:
                System.out.println("축하합니다! 2등 당첨!");
                break;
            case THIRD:
                System.out.println("축하합니다! 3등 당첨!");
                break;
            case FOURTH:
                System.out.println("축하합니다! 4등 당첨!");
                break;
            case FIFTH:
                System.out.println("축하합니다! 5등 당첨!");
                break;
            case NONE:
                System.out.println("아쉽지만 낙첨입니다.");
                break;
        }
    }
}
