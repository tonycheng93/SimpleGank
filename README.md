#	干货集中营API文档

###	1、获取分类数据

格式：http://gank.io/api/data/数据类型/请求个数/第几页

参数解释：

|  参数  |                    备注                    |
| :--: | :--------------------------------------: |
| 数据类型 | 支持“福利\|Android\|iOS\|休息视频\|拓展资源\|前端\|all” |
| 请求个数 |                 数字（大于0）                  |
| 第几页  |                 数字（大于0）                  |

示例：

[http://gank.io/api/data/福利/10/1](http://gank.io/api/data/福利/10/1)

[http://gank.io/api/data/Android/10/1](http://gank.io/api/data/Android/10/1)

[http://gank.io/api/data/iOS/10/1](http://gank.io/api/data/iOS/10/1)

[http://gank.io/api/data/all/10/1](http://gank.io/api/data/all/10/1)

请求数据示例：([http://gank.io/api/data/Android/10/1](http://gank.io/api/data/Android/10/1))

```json
{
  "error": false,
  "results": [
    {
      "_id": "57e7e7fd421aa95de3b8aad9",
      "createdAt": "2016-09-25T23:06:37.257Z",
      "desc": "支持左右两边同时滚动的进度条",
      "images": [
      "http://img.gank.io/08a79d2c-d17d-41df-8e93-f8c7322ca99e"
      ],
      "publishedAt": "2016-10-13T11:30:10.490Z",
      "source": "web",
      "type": "Android",
      "url": "https://github.com/alex5241/AwesomeProgressbar",
      "used": true,
      "who": "alex"
    }
  ]
}
```

个别参数解释：

|   参数   |                    备注                    |
| :----: | :--------------------------------------: |
| images | 干货配图（该项可能存在无数据的情况），若只需要宽度为100的图片，而不需要原图时，只需要在图片链接后面加上参数'?imageView2/0/w/100'即可，示例：http://img.gank.io/08a79d2c-d17d-41df-8e93-f8c7322ca99e?imageView2/0/w/100 |
|  url   |                干货数据的原始链接                 |

###	2、获取每日数据

格式：http://gank.io/api/day/年/月/日

示例：[http://gank.io/api/day/2016/10/14](http://gank.io/api/day/2016/10/14)

请求数据示例：

```json
{
  "category": [
    "休息视频",
    "前端",
    "Android",
    "iOS",
    "福利",
    "拓展资源"
  ],
  "error": false,
  "results": {
    "Android": [
      {
        "_id": "57ff53c3421aa95ddb9cb5de",
        "createdAt": "2016-10-13T17:28:35.999Z",
        "desc": "打造鲁棒的安卓APP，从此告别 Activity Not Found、Activity State Loss 和 fragment transaction 中的 NPE",
        "publishedAt": "2016-10-14T11:34:54.723Z",
        "source": "web",
        "type": "Android",
        "url": "https://github.com/Piasy/SafelyAndroid",
        "used": true,
        "who": "Piasy"
      }
    ],
    "iOS": [
      {
        "_id": "57ff5e90421aa95dd78e8e0d",
        "createdAt": "2016-10-13T18:14:40.670Z",
        "desc": "Mac 环境初始化 Python 写的自动打包ipa的系统 (超实用)",
        "publishedAt": "2016-10-14T11:34:54.723Z",
        "source": "web",
        "type": "iOS",
        "url": "https://github.com/CNKCQ/MacSetup.git",
        "used": true,
        "who": "CNKCQ"
      }
    ],
    "休息视频": [
      {
        "_id": "57fee39a421aa95ddb9cb5d2",
        "createdAt": "2016-10-13T09:30:02.164Z",
        "desc": "自从手机爆炸事件发生后，这个版本大概是三星被黑得最惨的一次... ...[doge][拜拜]",
        "publishedAt": "2016-10-14T11:34:54.723Z",
        "source": "chrome",
        "type": "休息视频",
        "url": "http://weibo.com/p/2304444bfb93b44f1222c6a25013d668cb5c1a",
        "used": true,
        "who": "lxxself"
      }
    ],
    "前端": [
      {
        "_id": "57ff1863421aa95ddb9cb5da",
        "createdAt": "2016-10-13T13:15:15.41Z",
        "desc": "JS 实现的 60 多种语言的 OCR 库",
        "images": [
          "http://img.gank.io/272ee9ed-4ca1-42d3-a5ec-0ac3b002b367"
        ],
        "publishedAt": "2016-10-14T11:34:54.723Z",
        "source": "chrome",
        "type": "前端",
        "url": "https://github.com/naptha/tesseract.js",
        "used": true,
        "who": "代码家"
      }
    ],
    "拓展资源": [
      {
        "_id": "5800248a421aa95de3b8abab",
        "createdAt": "2016-10-14T08:19:22.253Z",
        "desc": "awesome-备忘录系列：收集了不同语言和技术的一些实用 tips。",
        "publishedAt": "2016-10-14T11:34:54.723Z",
        "source": "chrome",
        "type": "拓展资源",
        "url": "https://github.com/detailyang/awesome-cheatsheet",
        "used": true,
        "who": "机器人"
      }
    ],
    "福利": [
      {
        "_id": "58001f88421aa95dd351b126",
        "createdAt": "2016-10-14T07:58:00.288Z",
        "desc": "10-14",
        "publishedAt": "2016-10-14T11:34:54.723Z",
        "source": "chrome",
        "type": "福利",
        "url": "http://ww1.sinaimg.cn/large/610dc034jw1f8rgvvm5htj20u00u0q8s.jpg",
        "used": true,
        "who": "代码家"
      }
    ]
  }
}
```

###	3、搜索API

格式：http://gank.io/api/search/query/listview/category/分类/count/数字/page/1

示例：[http://gank.io/api/search/query/listview/category/Android/count/10/page/1 ](http://gank.io/api/search/query/listview/category/Android/count/10/page/1)

参数解释：

|    参数    |                    备注                    |
| :------: | :--------------------------------------: |
| category | 后面可接受参数（ all \| Android \| iOS \| 休息视频 \| 福利 \| 拓展资源 \| 前端 \| 瞎推荐 \| App |
|  count   |                数字，最大支持50                 |
|   page   |                    数字                    |

请求数据示例：

```json
{
  "count": 2,
  "error": false,
  "results": [
    {
      "desc": "还在用ListView？",
      "ganhuo_id": "57334c9d67765903fb61c418",
      "publishedAt": "2016-05-12T12:04:43.857000",
      "readability": "<div>一段HTML代码</div>",
      "type": "Android",
      "url": "http://www.jianshu.com/p/a92955be0a3e",
      "who": "陈宇明"
    },
    {
      "desc": "listview的折叠效果",
      "ganhuo_id": "56cc6d1d421aa95caa7076fa",
      "publishedAt": "2015-07-17T03:43:22.395000",
      "readability": "<div>一段HTMl代码</div>",
      "type": "Android",
      "url": "https://github.com/dodola/ListItemFold",
      "who": "Jason"
    }
  ]
}
```



##	Dependency

> * [RxJava](https://github.com/ReactiveX/RxJava)
> * [RxAndroid](https://github.com/ReactiveX/RxAndroid)
> * [Glide](https://github.com/bumptech/glide)
> * [Retrofit](https://square.github.io/retrofit/)
> * [LeakCanary](https://github.com/square/leakcanary)

##	Thanks

> * [干货集中营](http://gank.io)
> * [UltimateRecyclerView](https://github.com/cymcsg/UltimateRecyclerView)

##	About me

> * Email:tonycheng93@gmail.com
> * Blog:[http://tonycheng93.github.io/](http://tonycheng93.github.io/)
