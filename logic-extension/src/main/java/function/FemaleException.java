package function;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2020-04-10.17:36
 */
public class FemaleException implements HumanException{

    @Override
    public int plus(int var){
        throw new RuntimeException("异常");
//        return var+100;
    }
}
