/**
 * @author : yu.zhang
 * Date : 2018/10/10 上午11:13
 * Email : zephyrjung@126.com
 * 为要操作的对象添加一层包装，以进行各种控制
 * 比如英雄本身只有上下左右四种移动，通过代理，可以添加额外的斜方向移动，写方向移动是通过两个正向移动叠加实现的的
 * 添加对象的代理，可以简化一些复杂的对象行为，是对象行为更纯粹更有事务性
 * @see pattern/decorator/package-info.java
 **/
package org.b3log.pattern.proxy;