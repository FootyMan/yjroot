package com.api.business;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.request.OrderRequest;
import com.api.request.baseRequest;
import com.api.response.AlipayResponse;
import com.api.response.WxResponse;
import com.api.response.BaseResponse;
import com.api.response.ProductDetailResponse;
import com.api.response.ProductsResponse;
import com.service.alipay.sdk.AlipayPayManager;
import com.service.api.impl.OrderServiceImpl;
import com.service.api.impl.ProductServiceImpl;
import com.service.bean.Order;
import com.service.bean.Product;
import com.service.utils.StringUtils;

@Service("OrderBusiness")
public class OrderBusiness {

	@Autowired
	private ProductServiceImpl productServiceImpl;
	@Autowired
	private OrderServiceImpl orderServiceImpl;

	/**
	 * 订单支付获取支付信息
	 * 
	 * @param request
	 * @return
	 */
	public BaseResponse<?> PayOrder(baseRequest<OrderRequest> request) {

		OrderRequest orderModel = request.getbody();
		Product product = productServiceImpl.selectProductById(orderModel.getProductId());
		//订单号
		String orderNumber=StringUtils.GetOrderNumber(request.getUserId(), product.getProductId());
		if (product != null && product.getProductId() > 0) {
			// 生成订单
			Order order = new Order();
			order.setOrderNumber(orderNumber);
			order.setUserId(request.getUserId());
			order.setProductId(product.getProductId());
			order.setOrderSource(1);
			order.setOrderPrice(product.getPrice());
			order.setOrderSource(request.getDeviceType());
			order.setPayType(orderModel.getPayType());
			orderServiceImpl.insertOrder(order);
		}
		if (orderModel.getPayType() == 1) {
			BaseResponse<WxResponse> wxResponse = new BaseResponse<WxResponse>();
			WxResponse wx = new WxResponse();
			wx.setAppid("Appid");
			wx.setPartnerid("Partnerid");
			wx.setPrepayid("prepayid");
			wx.setTrade_type("trade_type");
			wx.setSign("sign");
			wx.setTimeStamp("timeStamp");
			wx.setNonce_str("nonce_str");
			wxResponse.setData(wx);
			return wxResponse;
		}
		// 支付宝
		else if (orderModel.getPayType() == 2) {
			BaseResponse<AlipayResponse> aliPayResponse = new BaseResponse<AlipayResponse>();

			AlipayResponse alipayResponseModel = new AlipayResponse();
			AlipayPayManager alipayManager = new AlipayPayManager();
			// 获取支付宝订单字符串
			String orderString = alipayManager.GetOrderString(orderNumber, product.getProductDesc(),
					String.valueOf(product.getPrice()));
			alipayResponseModel.setAlipayString(orderString);
			aliPayResponse.setData(alipayResponseModel);
			return aliPayResponse;
		}
		return null;
	}

	/**
	 * 获取购买产品
	 * 
	 * @param request
	 * @return
	 */
	public BaseResponse<ProductsResponse> GetProducts(baseRequest<?> request) {
		List<Product> products = productServiceImpl.selectProductlist();

		BaseResponse<ProductsResponse> response = new BaseResponse<ProductsResponse>();
		ProductsResponse ps = new ProductsResponse();
		List<ProductDetailResponse> listProduct = new ArrayList<ProductDetailResponse>();
		for (Product product : products) {
			ProductDetailResponse x = new ProductDetailResponse();
			x.setPrice(product.getPrice());
			x.setProductId(product.getProductId());
			x.setDesc(product.getProductDesc());
			x.setTitle(product.getTitle());
			listProduct.add(x);
		}
		ps.setProducts(listProduct);
		response.setData(ps);
		return response;
	}
}
