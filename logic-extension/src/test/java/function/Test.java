package function;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2020-04-10.17:05
 */
public class Test {

    private static AbstractComponentExecutor componentExecutor = new AbstractComponentExecutor();


    private static void test1() throws Exception {
        CheckedFunction<Integer, String, Exception> checkedFunction = integer -> integer.toString();

        CheckedFunction<String, Integer, Exception> testFunctiona2 = integer -> Integer.valueOf(integer);

        System.out.println(checkedFunction.apply(new Integer(1)));
        System.out.println(testFunctiona2.apply("2"));
    }

    private static void test2() {
        String gender = "Male";
        int result = componentExecutor.execute(Human.class, gender, func -> func.plus(1));
        System.out.println(result);
        gender = "Female";
        result = componentExecutor.execute(Human.class, gender, func -> func.plus(1));
        System.out.println(result);
    }

    private static void test3() throws Exception{
        String gender = "MException";
        int result = componentExecutor.executeCheckedFunc(HumanException.class, gender, func -> func.plus(1));
        System.out.println(result);
        gender = "FException";
        result = componentExecutor.executeCheckedFunc(HumanException.class, gender, func -> func.plus(1));
        System.out.println(result);
    }


    public static void main(String[] args) throws Exception {
        Test.test1();
        Test.test2();
        test3();
    }
}
