package opentuned.callback;

/**
 * Created by stefan.cross on 16/02/2016.
 *
 * An example of a callback between classes taken from Diego
 *
 * http://stackoverflow.com/questions/18279302/how-do-i-perform-a-java-callback-between-classes
 *
 * Note that by using an interface we gain the dexterity and flexibility to register any class
 * which implements the interface with Caller rather then just the implementation.
 *
 */

interface ICallBack{
    void methodToCallback();
}

class CallBackImpl implements ICallBack {
    public void methodToCallback() {
        System.out.println("I've been called back");
    }
}

class Caller {

    public void register(ICallBack callBack){
        System.out.println("callback registered");
        callBack.methodToCallback();
    }

    public static void main(String[] args){
        Caller tester = new Caller();
        ICallBack callBack = new CallBackImpl();
        tester.register(callBack);
    }
}
