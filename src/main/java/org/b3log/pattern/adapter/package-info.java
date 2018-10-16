/**
 * @author : yu.zhang
 * Date : 2018/10/15 下午6:38
 * Email : yu.zhang@7fresh.com
 * 适配器模式
 * patterns中，船长Captain只能接受RowingBoat接口的实现，而当前只有FishingBoat实现，为了适配，定义了FishingBoatAdapter，实现了RowingBoat接口，并在其中进行相关调用
 * 以我理解，适配器模式和代理模式的区别在于，代理模式是有计划的添加了中间层去控制要调用的对象，而适配器模式则是后补添加来适应要调用的对象的
 * 代理由于是提前进行的，一般提供的方法比要调用的对象只多不少，而后补的适配器，是适应调用方与被调用方的差异，则可能比要调用的对象方法少也可能比要调用的对象方法多
 * 但实现形式应当是没有差别的
 *
 * 原先的英雄是三个技能的，后来出现了四个技能的英雄，为此，定义PlayerAdapter，适应四技能英雄
 * 如何实现适配模式，尚未定论
 **/
package org.b3log.pattern.adapter;