# 接口说明 #

时间： 2014/4/4 23:06:02

## 获取当前所有用户 ##

URL： [https://gdmcmm-jqmobile.rhcloud.com/?json=users/get_users&dev=1](https://gdmcmm-jqmobile.rhcloud.com/?json=users/get_users&dev=1)

示例：

	{
	  "status": "ok",
	  "0": {
	    "user_id": "1",
	    "ID": "1",
	    "user_login": "admin",
	    "display_name": "admin",
	    "user_email": "summit_mail@qq.com",
	    "meta_value": "a:1:{s:13:\"administrator\";b:1;}"
	  }
	}

## 用户登陆 ##

URL: [https://gdmcmm-jqmobile.rhcloud.com/?json=users/login&dev=1&user_login=用户名&user_password=密码](https://gdmcmm-jqmobile.rhcloud.com/?json=users/login&dev=1&user_login=用户名&user_password=密码)

示例：

	{
	  "status": "ok",
	  "message": "Successfully Logged In"
	}

## 用户注销 ##

URL: [https://gdmcmm-jqmobile.rhcloud.com/?json=users/logout&dev=1](https://gdmcmm-jqmobile.rhcloud.com/?json=users/logout&dev=1)

示例：

	{
	  "status": "ok",
	  "message": "Successfully Logged Out"
	}

## 创建用户 ##

### 获得令牌 ###

URL: [https://gdmcmm-jqmobile.rhcloud.com/?json=get_nonce&dev=1&controller=Users&method=create_user](https://gdmcmm-jqmobile.rhcloud.com/?json=get_nonce&dev=1&controller=Users&method=create_user)

示例：

	{
	  "status": "ok",
	  "controller": "users",
	  "method": "create_user",
	  "nonce": "616862af45"
	}

### 使用令牌创建用户 ###

URL: [https://gdmcmm-jqmobile.rhcloud.com/?json=users/create_user&dev=1&u=管理员用户名&p=管理员密码&user_login=新的用户名&user_password=密码&user_email=新用户邮箱&nonce=令牌&role=author](https://gdmcmm-jqmobile.rhcloud.com/?json=users/create_user&dev=1&u=管理员用户名&p=管理员密码&user_login=新的用户名&user_password=密码&user_email=新用户邮箱&nonce=令牌&role=author)

示例：

	{
	  "status": "ok",
	  "user": {
	    "data": {
	      "ID": "2",
	      "user_login": "test",
	      "user_pass": "$P$BqScCH5DdB0AWONAC9BqoJYayWiZHT.",
	      "user_nicename": "test",
	      "user_email": "test@qq.com",
	      "user_url": "",
	      "user_registered": "2014-04-04 15:17:08",
	      "user_activation_key": "",
	      "user_status": "0",
	      "display_name": "test"
	    },
	    "ID": 2,
	    "caps": {
	      "subscriber": true
	    },
	    "cap_key": "wp_capabilities",
	    "roles": [
	      "subscriber"
	    ],
	    "allcaps": {
	      "read": true,
	      "level_0": true,
	      "subscriber": true
	    },
	    "filter": null
	  }
	}

## 获取所有秘密 ##

URL: [https://gdmcmm-jqmobile.rhcloud.com/?json=get_posts&dev=1&custom_fields=like_num](https://gdmcmm-jqmobile.rhcloud.com/?json=get_posts&dev=1&custom_fields=like_num)

示例：

	{
	  "status": "ok",
	  "count": 1,
	  "count_total": 1,
	  "pages": 1,
	  "posts": [
	    {
	      "id": 1,
	      "type": "post",
	      "slug": "hello-world",
	      "url": "https:\/\/gdmcmm-jqmobile.rhcloud.com\/?p=1",
	      "status": "publish",
	      "title": "\u4e16\u754c\uff0c\u4f60\u597d\uff01",
	      "title_plain": "\u4e16\u754c\uff0c\u4f60\u597d\uff01",
	      "content": "<p>\u6b22\u8fce\u4f7f\u7528WordPress\u3002\u8fd9\u662f\u7cfb\u7edf\u81ea\u52a8\u751f\u6210\u7684\u6f14\u793a\u6587\u7ae0\u3002\u7f16\u8f91\u6216\u8005\u5220\u9664\u5b83\uff0c\u7136\u540e\u5f00\u59cb\u60a8\u7684\u535a\u5ba2\uff01<\/p>\n",
	      "excerpt": "\u6b22\u8fce\u4f7f\u7528WordPress\u3002\u8fd9\u662f\u7cfb\u7edf\u81ea\u52a8\u751f\u6210\u7684\u6f14\u793a\u6587\u7ae0\u3002\u7f16\u8f91\u6216\u8005\u5220\u9664\u5b83\uff0c\u7136\u540e\u5f00\u59cb\u60a8\u7684\u535a\u5ba2\uff01",
	      "date": "2014-04-04 12:25:51",
	      "modified": "2014-04-04 12:25:51",
	      "categories": [],
	      "tags": [],
	      "author": {
	        "id": 1,
	        "slug": "admin",
	        "name": "admin",
	        "first_name": "",
	        "last_name": "",
	        "nickname": "admin",
	        "url": "",
	        "description": ""
	      },
	      "comments": [
	        {
	          "id": 1,
	          "name": "WordPress\u5148\u751f",
	          "url": "http:\/\/wordpress.org\/",
	          "date": "2014-04-04 12:25:51",
	          "content": "<p>\u60a8\u597d\uff0c\u8fd9\u662f\u4e00\u6761\u8bc4\u8bba\u3002<br \/>\n\u8981\u5220\u9664\u8bc4\u8bba\uff0c\u8bf7\u5148\u767b\u5f55\uff0c\u7136\u540e\u518d\u67e5\u770b\u8fd9\u7bc7\u6587\u7ae0\u7684\u8bc4\u8bba\u3002\u767b\u5f55\u540e\u60a8\u53ef\u4ee5\u770b\u5230\u7f16\u8f91\u6216\u8005\u5220\u9664\u8bc4\u8bba\u7684\u9009\u9879\u3002<\/p>\n",
	          "parent": 0
	        }
	      ],
	      "attachments": [],
	      "comment_count": 1,
	      "comment_status": "open"
	    }
	  ]
	}

## 获取秘密 ##

URL: [https://gdmcmm-jqmobile.rhcloud.com/?json=get_post&dev=1&id=私密id&custom_fields=like_num](https://gdmcmm-jqmobile.rhcloud.com/?json=get_post&dev=1&id=私密id&custom_fields=like_num)

示例：
	
	{
	  "status": "ok",
	  "post": {
	    "id": 1,
	    "type": "post",
	    "slug": "hello-world",
	    "url": "https:\/\/gdmcmm-jqmobile.rhcloud.com\/?p=1",
	    "status": "publish",
	    "title": "\u4e16\u754c\uff0c\u4f60\u597d\uff01",
	    "title_plain": "\u4e16\u754c\uff0c\u4f60\u597d\uff01",
	    "content": "<p>\u6b22\u8fce\u4f7f\u7528WordPress\u3002\u8fd9\u662f\u7cfb\u7edf\u81ea\u52a8\u751f\u6210\u7684\u6f14\u793a\u6587\u7ae0\u3002\u7f16\u8f91\u6216\u8005\u5220\u9664\u5b83\uff0c\u7136\u540e\u5f00\u59cb\u60a8\u7684\u535a\u5ba2\uff01<\/p>\n",
	    "excerpt": "\u6b22\u8fce\u4f7f\u7528WordPress\u3002\u8fd9\u662f\u7cfb\u7edf\u81ea\u52a8\u751f\u6210\u7684\u6f14\u793a\u6587\u7ae0\u3002\u7f16\u8f91\u6216\u8005\u5220\u9664\u5b83\uff0c\u7136\u540e\u5f00\u59cb\u60a8\u7684\u535a\u5ba2\uff01",
	    "date": "2014-04-04 12:25:51",
	    "modified": "2014-04-04 12:25:51",
	    "categories": [],
	    "tags": [],
	    "author": {
	      "id": 1,
	      "slug": "admin",
	      "name": "admin",
	      "first_name": "",
	      "last_name": "",
	      "nickname": "admin",
	      "url": "",
	      "description": ""
	    },
	    "comments": [
	      {
	        "id": 1,
	        "name": "WordPress\u5148\u751f",
	        "url": "http:\/\/wordpress.org\/",
	        "date": "2014-04-04 12:25:51",
	        "content": "<p>\u60a8\u597d\uff0c\u8fd9\u662f\u4e00\u6761\u8bc4\u8bba\u3002<br \/>\n\u8981\u5220\u9664\u8bc4\u8bba\uff0c\u8bf7\u5148\u767b\u5f55\uff0c\u7136\u540e\u518d\u67e5\u770b\u8fd9\u7bc7\u6587\u7ae0\u7684\u8bc4\u8bba\u3002\u767b\u5f55\u540e\u60a8\u53ef\u4ee5\u770b\u5230\u7f16\u8f91\u6216\u8005\u5220\u9664\u8bc4\u8bba\u7684\u9009\u9879\u3002<\/p>\n",
	        "parent": 0
	      }
	    ],
	    "attachments": [],
	    "comment_count": 1,
	    "comment_status": "open"
	  }
	}

## 获取指定时间的秘密 ##

URL: [https://gdmcmm-jqmobile.rhcloud.com/?json=get_date_posts&dev=1&date=2014-04-04](https://gdmcmm-jqmobile.rhcloud.com/?json=get_date_posts&dev=1&date=2014-04-04)

示例：

	{
	  "status": "ok",
	  "count": 1,
	  "count_total": 1,
	  "pages": 1,
	  "posts": [
	    {
	      "id": 1,
	      "type": "post",
	      "slug": "hello-world",
	      "url": "https:\/\/gdmcmm-jqmobile.rhcloud.com\/?p=1",
	      "status": "publish",
	      "title": "\u4e16\u754c\uff0c\u4f60\u597d\uff01",
	      "title_plain": "\u4e16\u754c\uff0c\u4f60\u597d\uff01",
	      "content": "<p>\u6b22\u8fce\u4f7f\u7528WordPress\u3002\u8fd9\u662f\u7cfb\u7edf\u81ea\u52a8\u751f\u6210\u7684\u6f14\u793a\u6587\u7ae0\u3002\u7f16\u8f91\u6216\u8005\u5220\u9664\u5b83\uff0c\u7136\u540e\u5f00\u59cb\u60a8\u7684\u535a\u5ba2\uff01<\/p>\n",
	      "excerpt": "\u6b22\u8fce\u4f7f\u7528WordPress\u3002\u8fd9\u662f\u7cfb\u7edf\u81ea\u52a8\u751f\u6210\u7684\u6f14\u793a\u6587\u7ae0\u3002\u7f16\u8f91\u6216\u8005\u5220\u9664\u5b83\uff0c\u7136\u540e\u5f00\u59cb\u60a8\u7684\u535a\u5ba2\uff01",
	      "date": "2014-04-04 12:25:51",
	      "modified": "2014-04-04 12:25:51",
	      "categories": [],
	      "tags": [],
	      "author": {
	        "id": 1,
	        "slug": "admin",
	        "name": "admin",
	        "first_name": "",
	        "last_name": "",
	        "nickname": "admin",
	        "url": "",
	        "description": ""
	      },
	      "comments": [
	        {
	          "id": 1,
	          "name": "WordPress\u5148\u751f",
	          "url": "http:\/\/wordpress.org\/",
	          "date": "2014-04-04 12:25:51",
	          "content": "<p>\u60a8\u597d\uff0c\u8fd9\u662f\u4e00\u6761\u8bc4\u8bba\u3002<br \/>\n\u8981\u5220\u9664\u8bc4\u8bba\uff0c\u8bf7\u5148\u767b\u5f55\uff0c\u7136\u540e\u518d\u67e5\u770b\u8fd9\u7bc7\u6587\u7ae0\u7684\u8bc4\u8bba\u3002\u767b\u5f55\u540e\u60a8\u53ef\u4ee5\u770b\u5230\u7f16\u8f91\u6216\u8005\u5220\u9664\u8bc4\u8bba\u7684\u9009\u9879\u3002<\/p>\n",
	          "parent": 0
	        }
	      ],
	      "attachments": [],
	      "comment_count": 1,
	      "comment_status": "open"
	    }
	  ]
	}

## 发布秘密 ##

### 获取令牌 ###

URL: [https://gdmcmm-jqmobile.rhcloud.com/?json=get_nonce&dev=1&controller=Posts&method=create_post](https://gdmcmm-jqmobile.rhcloud.com/?json=get_nonce&dev=1&controller=Posts&method=create_post)

示例：

	{
	  "status": "ok",
	  "controller": "posts",
	  "method": "create_post",
	  "nonce": "059aab62f4"
	}

### 使用令牌发布私密 ###

URL: [https://gdmcmm-jqmobile.rhcloud.com/?json=posts/create_post&dev=1&nonce=059aab62f4&status=publish&like_num=0&title=%E7%A7%98%E5%AF%86&content=%E6%98%8E%E5%A4%A9%E4%BD%A0%E8%A6%81%E7%9C%8B%E7%9A%84%E9%A1%B9%E7%9B%AE%E6%98%AF%EF%BC%9F](https://gdmcmm-jqmobile.rhcloud.com/?json=posts/create_post&dev=1&nonce=059aab62f4&status=publish&like_num=0&title=%E7%A7%98%E5%AF%86&content=%E6%98%8E%E5%A4%A9%E4%BD%A0%E8%A6%81%E7%9C%8B%E7%9A%84%E9%A1%B9%E7%9B%AE%E6%98%AF%EF%BC%9F)

示例：

	{
	  "status": "ok",
	  "post": {
	    "id": 10,
	    "type": "post",
	    "slug": "%e7%a7%98%e5%af%86",
	    "url": "https:\/\/gdmcmm-jqmobile.rhcloud.com\/?p=10",
	    "status": "publish",
	    "title": "\u79d8\u5bc6",
	    "title_plain": "\u79d8\u5bc6",
	    "content": "<p>\u660e\u5929\u4f60\u8981\u770b\u7684\u9879\u76ee\u662f\uff1f<\/p>\n",
	    "excerpt": "\u660e\u5929\u4f60\u8981\u770b\u7684\u9879\u76ee\u662f\uff1f",
	    "date": "2014-04-04 12:25:51",
	    "modified": "2014-04-04 23:34:03",
	    "categories": [],
	    "tags": [],
	    "author": {
	      "id": 1,
	      "slug": "admin",
	      "name": "admin",
	      "first_name": "",
	      "last_name": "",
	      "nickname": "admin",
	      "url": "",
	      "description": ""
	    },
	    "comments": [],
	    "attachments": [],
	    "comment_count": 0,
	    "comment_status": "open"
	  }
	}


## 用户评论 ##

URL: [http://gdmcmm.tk/?json=respond/submit_comment&dev=1&post_id=27&content=我喜欢秘密&name=admin&email=summit_mail@qq.com&comment_parent=4](http://gdmcmm.tk/?json=respond/submit_comment&dev=1&post_id=27&content=我喜欢秘密&name=admin&email=summit_mail@qq.com&comment_parent=4)

说明：

- post_id: 秘密id
- content: 评论内容
- name: 评论的用户名
- email: 评论的用户邮箱
- comment_parent: 可选，若是回复别人的评论，这里加入之前的评论id

示例：

	{
	  "status": "ok",
	  "id": 8,
	  "name": "test",
	  "url": "",
	  "date": "2014-04-10 11:10:45",
	  "content": "我喜欢秘密",
	  "parent": 4
	}

## 查询喜欢 ##

URL: [http://gdmcmm.tk/?json=posts/get_post_like&dev=1&blog_id=34](http://gdmcmm.tk/?json=posts/get_post_like&dev=1&blog_id=34)

说明：

- blog_id 为秘密id

示例：

	{
	  "status": "ok",
	  "like_num": "0"
	}

## 喜欢 ##

### 获取令牌 ###

URL: [https://gdmcmm-jqmobile.rhcloud.com/?json=get_nonce&dev=1&controller=Posts&method=updata_post_like](https://gdmcmm-jqmobile.rhcloud.com/?json=get_nonce&dev=1&controller=Posts&method=updata_post_like)

示例：

	{
	  "status": "ok",
	  "controller": "posts",
	  "method": "updata_post_like",
	  "nonce": "1ecbb864e5"
	}

### 更新喜欢 ###

URL: [https://gdmcmm-jqmobile.rhcloud.com/?json=posts/update_post_like&dev=1&blog_id=34&nonce=8797a64616&like_num=1](https://gdmcmm-jqmobile.rhcloud.com/?json=posts/update_post_like&dev=1&blog_id=34&nonce=8797a64616&like_num=1)

示例：

	{
	  "status": "ok",
	  "like_num": "1"
	}


## 获取用户信息 ##
URL: [https://gdmcmm-jqmobile.rhcloud.com/?json=users/get_userdata&dev=1&id=1](https://gdmcmm-jqmobile.rhcloud.com/?json=users/get_userdata&dev=1&id=1)

示例：

	{
	  "status": "ok",
	  "user": {
	    "data": {
	      "ID": "2",
	      "user_login": "test",
	      "user_pass": "$P$BqScCH5DdB0AWONAC9BqoJYayWiZHT.",
	      "user_nicename": "test",
	      "user_email": "test@qq.com",
	      "user_url": "",
	      "user_registered": "2014-04-04 15:17:08",
	      "user_activation_key": "",
	      "user_status": "0",
	      "display_name": "test"
	    },
	    "ID": 2,
	    "caps": {
	      "author": true
	    },
	    "cap_key": "wp_capabilities",
	    "roles": [
	      "author"
	    ],
	    "allcaps": {
	      "upload_files": true,
	      "edit_posts": true,
	      "edit_published_posts": true,
	      "publish_posts": true,
	      "read": true,
	      "level_2": true,
	      "level_1": true,
	      "level_0": true,
	      "delete_posts": true,
	      "delete_published_posts": true,
	      "author": true
	    },
	    "filter": null
	  }
	}