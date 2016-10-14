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

|   参数   |         备注         |
| :----: | :----------------: |
| images | 干货配图（该项可能存在无数据的情况） |
|  url   |     干货数据的原始链接      |