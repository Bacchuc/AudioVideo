## CDN网络介绍

#### 泛娱乐化直播架构
- 共享端(美女直播端：电脑、手机)发起信令到信令服务器，信令服务器进行逻辑处理(例如：创建房间、聊天、礼物都是以信令的方式进行传输处理的)，
- 流媒体云( CDN )：对音视频流( RTMP )进行转发，将信号转发给收看用户
![泛娱乐化直播架构](https://github.com/Bacchuc/AudioVideo/blob/master/notelib/src/main/java/com/bacchus/notelib/image/imooc_entertainment.png)

流程描述
- 共享端
    - 共享端发送信令到信令服务器，请求创建房间
    - 信令服务器收到信令后，创建房间，然后给共享端返回一个流媒体云的地址
    - 共享端采集自己的音视频数据，形成 RTMP 流上传到流媒体云网络
- 用户收看端
    - 用户发送请求加入房间的信令给信令服务器
    - 信令服务器将此用户加入到房间，然后返回此用户请求的该美女主播房间的 CDN 流地址
    - 用户通过此地址拉取此流从而观看该节目

可能会有很多架构变形，但是基本上万变不离其宗，大致都是这个样子

#### 实时互动直播架构
- TCP 协议：通过确认、超时、重发来使包的传输可以准确无误，但是也使其无法更好的进行实时传输，所以实时互动直播架构采用的是 UDP 协议，使用 UDP 协议就需要用到自有网络，不能只使用 CDN 网络了
- 音视频数据有时效性，所以允许丢包，但是丢包了就会影响质量
- 有多个服务节点，一旦某个节点有问题就会转移到其余节点，对于用户来说是无感知的
，也能保证负载均衡
- 由于有多个节点，所以有一个控制中心，控制中心与节点之间是通过心跳包来联系的，隔一段时间，节点都会向控制中心发送此刻的：CPU 占用情况，IO 占用情况、网络占用情况等等
- 控制中心会根据这些信息来调拨这些节点，例如将数据交给 CPU 占用较低的节点处理
- 心跳包是通过内总线进行传输的，内总线的容量更高
- 以上就为实时互动架构，而通过媒体服务器进行转换给 CDN 网络，即将 UDP 协议转化为 RTMP 协议
![实时互动直播架构](https://github.com/Bacchuc/AudioVideo/blob/master/notelib/src/main/java/com/bacchus/notelib/image/imooc_realtime.png)

#### CDN 网络
- CDN 网络是为了解决用户访问网络资源慢而出现的技术
- 网络资源慢的原因
    - 数据链路长：海南访问北京的用户的数据，数据传输过程中还可能有节点会出现一些错误情况，如果将北京的服务直接搬到海南的服务中，就可以直接访问海南的服务了
    - 运营商之间为了利益不允许其他运营商的网络访问自己的网络，例如联通的不让访问电信的
- CDN 构成
    - 边缘节点：用户从边缘节点(指的是网络，不是物理距离)上获取数据
    - 二级节点：二级节点就是主干网节点，主要用于缓存大量数据，减轻源站压力。例如当边缘节点有数据时会直接返回给用户，无数据时，会向上访问主干节点，将数据拉取到自己的边缘节点再传输给用户，
    - 源站：CP (内容提供方)将内容放到源站，当主干节点也没数据时就会到源站节点获取数据
    - 有一些大型公司可能会因为数据很大，会分为更多层节点，但是大体上是这么一个结构
- CDN 网络
    - 在各大运营商中间设立主打节点，通过光纤将两个节点打通，这样就可以解决不同运营商之间的数据传输问题
    - 用户访问流程
        - 用户访问时会先访问 CDN 网络，然后 CDN 网络会判断该用户是什么运营商，然后找到最近的边缘节点，若边缘节点无数据，则向上请求主干节点，若主干节点依然没有数据则继续请求源站
        - 每次请求到达每一个节点，若是第一次，则会进行缓存，酱紫下一次请求或者其他用户请求时速度就会加快
    - CDN 网络的两种类型
        - 传统网络：热点型，若请求多次的会存在主干中，超时后删除
        - 直播类型：对延时性要求比较高，所以源站会一次性将所有数据 Push 到主干节点中，边缘节点直接从主干节点中进行拉取
![CDN 网络](https://github.com/Bacchuc/AudioVideo/blob/master/notelib/src/main/java/com/bacchus/notelib/image/imooc_cdn.png)

#### 工具
- FFmpeg: 
    - 只需要一个命令就可以将一个视频格式转换为其他视频格式
    - 音视频的抽取
    - 视频上打水印
- FFPlay
    - 使用命令行，不是图形化，是基于 FFmpeg 进行二次化开发的工具
- FlashPlayer
    - 用于对 RTMP 协议进行分析
    
#### 搭建流媒体服务
- 准备流媒体服务器(商业最好是 Linux，不建议用 Windows，所以平时用 Mac)
- 编译并安装 Nginx 服务( Web 服务器)
- 配置 RTMP 服务并启动 Nginx 服务

#### 安装配置步骤
- 到 iTerm 中输入：brew install nginx
- 此处可以直接百度搜索 nginx 的下载安装过程












