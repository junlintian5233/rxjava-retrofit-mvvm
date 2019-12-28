//package com.cnsunrun.commonui.utils;
//
//import java.io.File;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.view.View.OnClickListener;
//import cn.sharesdk.framework.PlatformActionListener;
//import cn.sharesdk.framework.ShareSDK;
//import cn.sharesdk.onekeyshare.OnekeyShare;
//import cn.sharesdk.onekeyshare.themes.classic.PlatformPage;
//
//import com.cnsunrun.overhaul.R;
//import com.cnsunrun.base.ApiInterface;
//import com.cnsunrun.bean.Home.HomeItem;
//import com.cnsunrun.support.net.NetRequestHandler;
//
//public class SharedUtil {
//	public static void setLins(OnClickListener lins) {
//		PlatformPage.lins = lins;
//	}
//
//	public static void showShare(final Context context,
//			final NetRequestHandler request, final HomeItem item) {
//		ShareSDK.initSDK(context);
//		OnekeyShare oks = new OnekeyShare();
////		if (Config.getLoginInfo().isGuanLi()) {
////			oks.setCustomerLogo(BitmapFactory.decodeResource(
////					context.getResources(), R.drawable.shangruanshuo), "尚软",
////					new OnClickListener() {
////
////						@Override
////						public void onClick(View v) {
////							if (request instanceof NetRequestHandler) {
////								ApiHelp.shareToCnsunrun(request, item.getId());
////							}
////						}
////					});
////		}
////		oks.addHiddenPlatform(TencentWeibo.NAME);
//		// oks.addHiddenPlatform(QZone.NAME);
////		oks.addHiddenPlatform(WechatFavorite.NAME);
//		// oks.addHiddenPlatform(WechatMoments.NAME);
//		// 关闭sso授权
//		oks.disableSSOWhenAuthorize();
//		// 分享时Notification的图标和文字 2.5.9以后的版本不调用此方法
//		// oks.setNotification(R.drawable.ic_launcher,
//		// getString(R.string.app_name));
//		// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
//		oks.setTitle(EmptyDeal.isEmpy(item.getFeed_title()) ? "来自闹客的分享" : item
//				.getFeed_title());
//		// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
//		oks.setTitleUrl(ApiInterface.DOWN);
//		// text是分享文本，所有平台都需要这个字段
//		oks.setText(item.getSharedContent());
//		// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//		if (EmptyDeal.isEmpy(item.getImgs())) {
//			oks.setImagePath(ic_luncher(context));// 确保SDcard下面存在此张图片
//		} else {
//			oks.setImageUrl(item.getImgs().get(0).getImg());
//			// oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
//		}
//		// url仅在微信（包括好友和朋友圈）中使用
//		oks.setUrl(ApiInterface.DOWN);
//		// comment是我对这条分享的评论，仅在人人网和QQ空间使用
//		// oks.setComment("我是测试评论文本");
//		// site是分享此内容的网站名称，仅在QQ空间使用
//		oks.setSite("闹客");
//		// siteUrl是分享此内容的网站地址，仅在QQ空间使用
//		oks.setSiteUrl(ApiInterface.DOWN);
//
//		// 启动分享GUI
//		oks.show(context);
//	}
//
//	public static void showSharenosrs(Context context, Object url,String imgUrl,String name) {
//		ShareSDK.initSDK(context);
//		OnekeyShare oks = new OnekeyShare();
//		// 关闭sso授权
//		oks.disableSSOWhenAuthorize();
//		// 分享时Notification的图标和文字 2.5.9以后的版本不调用此方法
//		// oks.setNotification(R.drawable.ic_launcher,
//		// getString(R.string.app_name));
//		// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
//		oks.setTitle(EmptyDeal.isEmpy(name) ? "来自闹客的分享" : name);
//		// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
//		oks.setTitleUrl(ApiInterface.DOWN);
//		// text是分享文本，所有平台都需要这个字段
//		oks.setText("我在闹客上发现了一个很有意思的人,分享给你");
//		// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//		if (EmptyDeal.isEmpy(imgUrl)) {
//			oks.setImagePath(ic_luncher(context));// 确保SDcard下面存在此张图片
//		} else {
//			oks.setImageUrl(imgUrl);
//			// oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
//		}
//		// url仅在微信（包括好友和朋友圈）中使用
//		oks.setUrl(ApiInterface.DOWN);
//		// comment是我对这条分享的评论，仅在人人网和QQ空间使用
//		// oks.setComment("我是测试评论文本");
//		// site是分享此内容的网站名称，仅在QQ空间使用
//		oks.setSite("闹客");
//		// siteUrl是分享此内容的网站地址，仅在QQ空间使用
//		oks.setSiteUrl(ApiInterface.DOWN);
//
//		// 启动分享GUI
//		oks.show(context);
//	}
//
//	public static void saveIcon(Context context) {
//		Bitmap bit = BitmapFactory.decodeResource(context.getResources(),
//				R.drawable.ic_launcher);
//		String path = ic_luncher(context);
//		if (!new File(path).exists())
//			UiUtils.saveBitmapToFile(bit, path);
//	}
//
//	public static String ic_luncher(Context context) {
//		return new File(context.getCacheDir(), "ic_launcher.jpg")
//				.getAbsolutePath();
//	}
//
//	public static void showShare(Context context, HomeItem item, String plat,
//			PlatformActionListener lin) {
//		ShareSDK.initSDK(context);
//		OnekeyShare oks = new OnekeyShare();
//		// oks.addHiddenPlatform(TencentWeibo.NAME);
//		// oks.addHiddenPlatform(WechatFavorite.NAME);
//		// 关闭sso授权
//		if (lin != null) {
//			oks.setCallback(lin);
//		}
//		oks.disableSSOWhenAuthorize();
//		// 分享时Notification的图标和文字 2.5.9以后的版本不调用此方法
//		// oks.setNotification(R.drawable.ic_launcher,
//		// getString(R.string.app_name));
//		// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
//		oks.setTitle(EmptyDeal.isEmpy(item.getFeed_title()) ? "来自闹客的分享" : item
//				.getFeed_title());
//		// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
//		oks.setTitleUrl("http://test.cnsunrun.com/qbk/");
//		// text是分享文本，所有平台都需要这个字段
//		oks.setText(item.getSharedContent());
//		// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//		if (EmptyDeal.isEmpy(item.getImgs())) {
//			oks.setImagePath(EmptyDeal.isEmpy(item.getImg()) ? ic_luncher(context)
//					: item.getImg());// 确保SDcard下面存在此张图片
//		}
//		// url仅在微信（包括好友和朋友圈）中使用
//		oks.setUrl("http://test.cnsunrun.com/qbk/");
//		// comment是我对这条分享的评论，仅在人人网和QQ空间使用
//		// oks.setComment("我是测试评论文本");
//		// site是分享此内容的网站名称，仅在QQ空间使用
//		oks.setSite("闹客");
//		// siteUrl是分享此内容的网站地址，仅在QQ空间使用
//		oks.setSiteUrl("http://test.cnsunrun.com/qbk/");
//		oks.setPlatform(plat);
//		// 启动分享GUI
//		oks.show(context);
//	}
//}
