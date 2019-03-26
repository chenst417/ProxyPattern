# ProxyPattern
代理模式：静态代理、JDK动态代理、cglib动态代理

1、仿JDK动态代理实现原理，自己手写一遍。
  
2、思考：为什么JDK动态代理中要求目标类实现的接口数量不能超过65535个？                   
    这个是Java 虚拟机规范规定的。               
    首先要知道Class类文件结构：             
    * Class文件是一组以8字节为基础单位的二进制流，              
    * 各个数据项目严格按照顺序紧凑排列在class文件中，                    
    * 中间没有任何分隔符，这使得class文件中存储的内容几乎是全部程序运行的程序。                               

    ClassFile 结构体，如下：
       ClassFile {
           u4             magic;
           u2             minor_version;
           u2             major_version;
           u2             constant_pool_count;
           cp_info        constant_pool[constant_pool_count-1];
           u2             access_flags;
           u2             this_class;
           u2             super_class;
           u2             interfaces_count;
           u2             interfaces[interfaces_count];
           u2             fields_count;
           field_info     fields[fields_count];
           u2             methods_count;
           method_info    methods[methods_count];
           u2             attributes_count;
           attribute_info attributes[attributes_count];
       }

     各项的含义描述：
     1，无符号数，以u1、u2、u4、u8分别代表1个字节、2个字节、4个字节、8个字节的无符号数
     2，表是由多个无符号数或者其它表作为数据项构成的复合数据类型，所以表都以“_info”结尾，
      由多个无符号数或其它表构成的复合数据类型
    看上边第18行 interfaces_count 接口计数器，interfaces_count 的值表示当前类或接口的直接父接口数量。类型是u2， 
    2个字节，即 2^16-1 = 65536-1 = 65535，所以目标类实现的接口数量不能超过65535个
    
3、结合自身的业务场景用代理模式进行重构。
