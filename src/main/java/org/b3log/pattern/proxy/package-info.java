/**
 * @author Zhang Yu
 * Date: 18年4月2日
 * Email: 2895205695@qq.com
 *
 * 这个模型是一个火车票购买的模型
 * 用户在网上购票，对于自己而言关注点是始发站，终点站，开车时间(Path)
 * 购票网站是一个代理系统(TicketCenter)，它承接用户请求，去内接的各个车站(TicketSellerHall)找到对应的车站系统(TicketSeller)检查是否有票，然后出票（Ticket），再交给用户
 * 用户则持车票去车站乘车(Train)
 *
 */
package org.b3log.pattern.proxy;