package baseball;

import java.util.ArrayList;
import java.util.List;

public class BaseballCount {

    public final int strikecount;
    public final int ballcount;

    public BaseballCount(List<Integer> computernums, List<Integer> usernums) {
        strikecount = StrikeCount(computernums, usernums);
        ballcount = BallCount(computernums, usernums);
    }


    private int BallCount(List<Integer> computernums, List<Integer> usernums) {
        int count = 0;
        for (int i = 0; i < computernums.size(); i++) {
            if (Ball(computernums, usernums, i)) {
                count++;
            }
        }
        return count;
    }

    private int StrikeCount(List<Integer> computernums, List<Integer> usernums) {
        int count = 0;
        for (int i = 0; i < computernums.size(); i++) {
            if (Strike(computernums, usernums, i)) {
                count++;
            }
        }
        return count;
    }

    private boolean Strike(List<Integer> computernum, List<Integer> usernum, int index) {
        return usernum.get(index) == computernum.get(index);
    }

    private boolean Ball(List<Integer> computernum, List<Integer> usernum, int index) {
        return computernum.contains(usernum.get(index)) && !Strike(computernum, usernum, index);
    }


}
