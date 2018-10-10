/**
 * @author : yu.zhang
 * Date : 2018/10/10 下午4:58
 * Email : yu.zhang@7fresh.com
 *
 * 状态模式
 * 对象的行为根据状态的不同而有所变化
 * 对象内部维护了状态，并且提供修改状态的方法，当状态改变时，对象的行为也改变
 *
 * 其与策略模式有点相近
 * "策略模式封装的是不同的算法，算法之间没有交互，以达到算法可以自由切换的目的；而状态模式封装的是不同的状态，以达到状态切换行为随之发生改变的目的"
 * 换句话说，策略模式替换的过程，状态模式替换的是结果
 *
 * 可以化用的例子如，普通攻击，有一定几率成为暴击，伤害X2
 **/
package org.b3log.pattern.state;