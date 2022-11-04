package baseball;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import camp.nextstep.edu.missionutils.Console;

public class Application {

    public static void main(String[] args) {
        // TODO: 프로그램 구현
        List<Integer> computernums = RandomNum(3);

        while(true){
            String usernum = userInput();
            confirmUserInput(usernum);
            List<Integer> usernums = stringConvertListInteger(usernum);
            BaseballCount baseballCount = new BaseballCount(computernums, usernums);

            if(baseballCount.strikecount == 3){
                System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
                break;
            }
            if(baseballCount.strikecount==0 && baseballCount.ballcount ==0){
                System.out.println("낫싱");
                continue;
            }
            System.out.printf("%d볼 %d스트라이크",baseballCount.ballcount,baseballCount.strikecount);
        }

    }

    public static List<Integer> stringConvertListInteger(String string) {
        List<Integer> list = new ArrayList<>();
        for (char chr : string.toCharArray()) {
            list.add(Character.getNumericValue(chr));
        }
        return list;
    }


    public static void confirmUserInput(String userInput) {
        List<Integer> list = new ArrayList<>();
        if (userInput.length() != 3) {
            throw new IllegalArgumentException("3자리 숫자가 아닙니다.");
        }
        for (char usernumchar : userInput.toCharArray()) {
            int usernum = Character.getNumericValue(usernumchar);
            if (!(usernumchar >= '0' && usernumchar <= '9')) {
                throw new IllegalArgumentException("숫자가 아닌 문자 입니다.");
            }
            if (list.contains(usernum)) {
                throw new IllegalArgumentException("서로 다른 수가 아닙니다.");
            }
            list.add(usernum);
        }
    }


    public static String userInput() {
        System.out.print("숫자를 입력해주세요 : ");
        String usernum = Console.readLine();
        return usernum;
    }


    public static List<Integer> RandomNum(int count) {
        List<Integer> list = new ArrayList<>();
        while (list.size() < count) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!list.contains(randomNumber)) {
                list.add(randomNumber);
            }
        }
        return list;
    }
}
