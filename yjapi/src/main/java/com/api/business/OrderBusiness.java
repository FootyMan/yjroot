package com.api.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.alipay.sdk.AlipayPayManager;
import com.api.request.OrderRequest;
import com.api.request.baseRequest;
import com.api.response.AlipayResponse;
import com.api.response.WxResponse;
import com.api.response.baseResponse;
import com.myErp.impl.OrderServiceImpl;
import com.myErp.impl.ProductServiceImpl;
import com.myErp.manager.bean.Order;
import com.myErp.manager.bean.Product;
import com.myErp.utils.StringUtils;

@Service("OrderBusiness")
public class OrderBusiness {

	@Autowired
	private ProductServiceImpl productServiceImpl;
	@Autowired
	private OrderServiceImpl orderServiceImpl;
	
	
	/**
	 * 订单支付获取支付信息
	 * @param request
	 * @return
	 */
	public baseResponse<?> PayOrder(baseRequest<OrderRequest> request)
	{
		
		OrderRequest orderModel = request.getbody();
		Product product = productServiceImpl.selectProductById(orderModel.getProductId());
		if (product != null && product.getProductId() > 0) {
			// 生成订单
			Order order = new Order();
			order.setOrderNumber(StringUtils.GetOrderNumber(request.getUserId(), product.getProductId()));
			order.setUserId(request.getUserId());
			order.setProductId(product.getProductId());
			order.setOrderSource(1);
			order.setOrderPrice(product.getPrice());
			order.setOrderSource(request.getDeviceType());
			order.setPayType(orderModel.getPayType());
			orderServiceImpl.insertOrder(order);
		}
		if (orderModel.getPayType() == 1) {
			baseResponse<WxResponse> wxResponse = new baseResponse<WxResponse>();
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
			baseResponse<AlipayResponse> aliPayResponse = new baseResponse<AlipayResponse>();

			AlipayResponse alipayResponseModel = new AlipayResponse();
			AlipayPayManager alipayManager = new AlipayPayManager();
			// 获取订单号
			String orderNumber = StringUtils.GetOrderNumber(request.getUserId(), orderModel.getProductId());
			// 获取支付宝订单字符串
			String orderString = alipayManager.GetOrderString(orderNumber, product.getProductDesc(),
					String.valueOf(product.getPrice()));
			alipayResponseModel.setAlipayString(orderString);
			aliPayResponse.setData(alipayResponseModel);
			return aliPayResponse;
		}
		return null;
	}
}
