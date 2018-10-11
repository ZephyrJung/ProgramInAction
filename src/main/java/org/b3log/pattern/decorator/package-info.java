/**
 * @author : yu.zhang
 * Date : 2018/10/11 下午5:07
 * Email : yu.zhang@7fresh.com
 *
 * "装饰器模式应当为所装饰的对象提供增强功能，而代理模式对所代理对象的使用施加控制，并不提供对象本身的增强功能。"
 * 本系列中，SelectedHero使用装饰器模式封装Hero，向Hero添加额外功能如公共技能
 * Player为Hero的代理，调用hero的方法执行各种操作
 **/
package org.b3log.pattern.decorator;