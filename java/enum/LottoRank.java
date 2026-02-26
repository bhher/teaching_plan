package a0219.enum_example;

public enum LottoRank {
    FIRST(6, "1등", 2000000000),      // 6개 일치
    SECOND(5, "2등", 50000000),       // 5개 일치
    THIRD(4, "3등", 1500000),         // 4개 일치
    FOURTH(3, "4등", 50000),         // 3개 일치
    FIFTH(2, "5등", 5000),           // 2개 일치
    NONE(0, "낙첨", 0);              // 당첨 없음
    
    private final int matchCount;     // 일치 개수
    private final String name;        // 등수 이름
    private final long prize;         // 상금
    
    LottoRank(int matchCount, String name, long prize) {
        this.matchCount = matchCount;
        this.name = name;
        this.prize = prize;
    }
    
    public int getMatchCount() {
        return matchCount;
    }
    
    public String getName() {
        return name;
    }
    
    public long getPrize() {
        return prize;
    }
    
    // 일치 개수로 등수 찾기
    public static LottoRank fromMatchCount(int matchCount) {
        for (LottoRank rank : values()) {
            if (rank.matchCount == matchCount) {
                return rank;
            }
        }
        return NONE;
    }
    
    // 당첨 여부 확인
    public boolean isWinner() {
        return this != NONE;
    }
}
