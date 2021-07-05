package com.wzl.j8new.designpatterns;


/**
 * @author wangzhilong
 * @date 2021/6/15 9:59
 * @Description: 单例模式
 */
/*通过枚举创单例是唯一不会被破坏的 因为枚举是线程安全的并且只会被jvm装载一次
    其它单例被破坏主要体现在两点：
        1、通过反射破坏 因为反射就是调用构造方法生成新的对象的 对于此种情况可以在代码种增加判断
            if (instance !=null){
                throw new RuntimeException("实例已经存在，请通过 getInstance()方法获取");
            }
        2、如果单例类实现了序列化接口Serializable, 就可以通过反序列化破坏单例，所以我们可以不实现序列化接口,如果非得实现序列化接口，可以重写反序列化方法readResolve(), 反序列化时直接返回相关单例对象。
            public Object readResolve() throws ObjectStreamException {
                return instance;
            }
    */
public class SingletonObject {
    private SingletonObject(){}
    private enum Singleton{
        INSTANCE;
        private final SingletonObject instance;

        Singleton() {
            instance = new SingletonObject();
        }

        private SingletonObject getInstance() {
            return instance;
        }
    }

    public static SingletonObject getInstance() {
        return Singleton.INSTANCE.getInstance();
    }
}
