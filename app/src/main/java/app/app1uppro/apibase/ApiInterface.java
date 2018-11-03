package app.app1uppro.apibase;


import java.util.HashMap;

import app.app1uppro.apibase.modelclass.CallOutsModel;
import app.app1uppro.apibase.modelclass.ChallengeListModel;
import app.app1uppro.apibase.modelclass.ChangePasswordModel;
import app.app1uppro.apibase.modelclass.CreateChallengeModel;
import app.app1uppro.apibase.modelclass.FollowedCategoryModel;
import app.app1uppro.apibase.modelclass.FriendProfileStatusModel;
import app.app1uppro.apibase.modelclass.FriendsListModel;
import app.app1uppro.apibase.modelclass.MessageDetailsModel;
import app.app1uppro.apibase.modelclass.MessageListModel;
import app.app1uppro.apibase.modelclass.PastVideosModel;
import app.app1uppro.apibase.modelclass.UploadVideoModel;
import app.app1uppro.apibase.modelclass.UserProfileModel;
import app.app1uppro.apibase.modelclass.LoginModel;
import app.app1uppro.apibase.modelclass.SucessModel;
import app.app1uppro.apibase.modelclass.VideoCategoryModel;
import app.app1uppro.apibase.modelclass.VideoSubCatModel;
import app.app1uppro.common.ApiConstants;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {

    @FormUrlEncoded
    @POST(ApiConstants.SIGNUP)
    Observable<SucessModel> signUp(@FieldMap HashMap<String, String> param);


    @FormUrlEncoded
    @POST(ApiConstants.LOGIN)
    Observable<LoginModel> login(@FieldMap HashMap<String, String> param);

    @FormUrlEncoded
    @POST(ApiConstants.FORGOT_PASSWORD)
    Observable<SucessModel> forgotPassword(@Field("username") String username,
                                           @Field("email") String email);


    @FormUrlEncoded
    @POST(ApiConstants.LOGOUT)
    Observable<SucessModel> logout(@Field("user_id") String user_id);


    @FormUrlEncoded
    @POST(ApiConstants.UPDATE_PROFILE)
    Observable<UserProfileModel> updateProfile(@Field("user_id") String user_id,
                                               @Field("username") String username,
                                               @Field("firstname") String firstname,
                                               @Field("lastname") String lastname);


    @FormUrlEncoded
    @POST(ApiConstants.GET_PROFILE)
    Observable<UserProfileModel> getProfile(@Field("user_id") String user_id);

    @Multipart
    @POST(ApiConstants.UPDATE_PROFILE_IMAGE)
    Observable<UserProfileModel> updateProfImage(@Part("user_id") RequestBody user_id,
                                                 @Part("profile_image\"; filename=\"image.jpg\" ") RequestBody file);

    @FormUrlEncoded
    @POST(ApiConstants.CHANGE_PASSWORD)
    Observable<ChangePasswordModel> changePassword(@Field("user_id") String user_id,
                                                   @Field("old_password") String old_password,
                                                   @Field("new_password") String new_password);

    @FormUrlEncoded
    @POST(ApiConstants.Send_Message)
    Observable<SucessModel> sendMessage(@Field("user_id") String user_id,
                                        @Field("user_to") String user_to,
                                        @Field("subject") String subject,
                                        @Field("message") String message);


    @FormUrlEncoded
    @POST(ApiConstants.View_Profile)
    Observable<FriendProfileStatusModel> view_Profile(@Field("user_id") String user_id,
                                                      @Field("profile_id") String profile_id);

    @FormUrlEncoded
    @POST(ApiConstants.VIDEO_CATEGORY)
    Observable<VideoCategoryModel> getVideoCategory(@Field("user_id") String userid);

    @FormUrlEncoded
    @POST(ApiConstants.Reply_Message)
    Observable<SucessModel> sendReplyMessage(@Field("user_id") String userid,
                                            @Field("message_id") String message_id,
                                            @Field("user_to") String user_to,
                                            @Field("message") String message);

    @FormUrlEncoded
    @POST(ApiConstants.Message_Details)
    Observable<MessageDetailsModel> getMessageDetail(@Field("user_id") String userid,
                                                     @Field("message_id") String message_id);

    @FormUrlEncoded
    @POST(ApiConstants.Update_Message_Read_Status)
    Observable<SucessModel> updateMessageReadStatus(@Field("user_id") String userid,
                                             @Field("message_id") String message_id);


    @FormUrlEncoded
    @POST(ApiConstants.VIDEO_SUB_CATEGORY)
    Observable<VideoSubCatModel> getSubVideoCategory(@Field("user_id") String userid,
                                                     @Field("videoCatID") String videoCatID);

    @FormUrlEncoded
    @POST(ApiConstants.Create_Challenge)
    Observable<CreateChallengeModel> createChallenge(@FieldMap HashMap<String, Object> param);


    @FormUrlEncoded
    @POST(ApiConstants.Add_Challenge_Video)
    Observable<SucessModel> add_Challenge_Video(@FieldMap HashMap<String, String> param);


    @FormUrlEncoded
    @POST(ApiConstants.Accept_Reject_Challenge)
    Observable<SucessModel> accept_Reject_Challenge(@FieldMap HashMap<String, String> param);

    @FormUrlEncoded
    @POST(ApiConstants.Challenge_List)
    Observable<ChallengeListModel> getChallenge_List(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST(ApiConstants.Challenge_HOME_VOTES)
    Observable<SucessModel> getHomeVotes(@Field("user_id") String user_id,
                                         @Field("videoID") String videoID,
                                         @Field("challengeID") String challengeID);

    @FormUrlEncoded
    @POST(ApiConstants.Past_Videos_Votes)
    Observable<SucessModel> getPastVideosVotes(@Field("user_id") String user_id,
                                               @Field("videoID") String videoID,
                                               @Field("challengeID") String challengeID,
                                               @Field("vote_type") String vote_type);


    @FormUrlEncoded
    @POST(ApiConstants.Call_Outs_List)
    Observable<CallOutsModel> getCallOuts_List(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST(ApiConstants.Add_Friend)
    Observable<SucessModel> add_Friend_method(@Field("user_id") String user_id,
                                              @Field("email") String email);

    @FormUrlEncoded
    @POST(ApiConstants.Get_Friend)
    Observable<FriendsListModel> friends_list_method(@Field("user_id") String user_id);


    @FormUrlEncoded
    @POST(ApiConstants.Get_Messages)
    Observable<MessageListModel> get_Messages_list(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST(ApiConstants.Accept_Reject_Friend_Request)
    Observable<SucessModel> friends_Accept_Reject(@Field("user_id") String user_id,
                                                  @Field("request_id") String id,
                                                  @Field("status") String type);


    @FormUrlEncoded
    @POST(ApiConstants.Followed_Category_List)
    Observable<FollowedCategoryModel> getCategoryFollowed_List(@Field("user_id") String user_id);


    @FormUrlEncoded
    @POST(ApiConstants.Accept_Reject_Challenge)
    Observable<SucessModel> getCallOuts_AcceptReject(@Field("user_id") String user_id,
                                                     @Field("challengeID") String challengeID,
                                                     @Field("videoEmbedID") String videoEmbedID,
                                                     @Field("videoName") String videoName,
                                                     @Field("videoDescription") String videoDescription,
                                                     @Field("type") String type);


    @FormUrlEncoded
    @POST(ApiConstants.Start_Follow_UnFollow)
    Observable<SucessModel> startFollowingUnfollowing(@Field("user_id") String user_id,
                                                      @Field("videoCatID") String videoCatID,
                                                      @Field("follow_status") String follow_status);

    @FormUrlEncoded
    @POST(ApiConstants.GetPastVideos)
    Observable<PastVideosModel> getPastVideos(@Field("user_id") String user_id,
                                              @Field("videoCatID") String videoCatID);


//    @FormUrlEncoded
//    @POST(ApiConstants.GET_PROFILE)
//    Observable<ProfileModel> getProfile(@Field("user_id") String user_id);
//
//    @Multipart
//    @POST(ApiConstants.UPDATE_PROFILE_IMAGE)
//    Observable<ProfileImageModel> updateProfImage(@Part("user_id") RequestBody user_id, @Part("profile_image\"; filename=\"image.jpg\" ") RequestBody file);
//
//
//    //user_id,old_password,new_password

//
//    //user_id,first_name,last_name,dob,email,gender
//
//    @FormUrlEncoded
//    @POST(ApiConstants.UPDATE_PROFILE)
//    Observable<UpdateProfModel> updateProfile(@Field("user_id") String user_id, @Field("first_name") String first_name, @Field("last_name") String last_name, @Field("dob") String dob, @Field("email") String email, @Field("gender") String gender);
//
//
//    @FormUrlEncoded
//    @POST(ApiConstants.GET_CATEGORIES)
//    Observable<GetCategoriesModel> getCategories(@Field("user_id") String user_id);
//
//
//    //category_id,pageNumber,filter_price,filter_category_id,filter_brand_id
//    @FormUrlEncoded
//    @POST(ApiConstants.GET_SUBCATEGORY)
//    Observable<ProductListingModel> getSubCategory(@FieldMap HashMap<String, String> param);
//
//
//    @FormUrlEncoded
//    @POST(ApiConstants.MAKE_FAVOURITE)
//    Observable<FavouriteModel> makeFavourite(@Field("user_id") String user_id, @Field("product_id") String product_id, @Field("type") String type);
//
//    @FormUrlEncoded
//    @POST(ApiConstants.GET_PRODUCT_DETAIL)
//    Observable<ProductDetailModel> getProductDetail(@Field("user_id") String user_id, @Field("product_id") String product_id, @Field("type") String type);
//
//
//    @FormUrlEncoded
//    @POST(ApiConstants.GET_FAVOURITE_lIST)
//    Observable<FavListModel> getFavouriteList(@Field("user_id") String user_id);
//
//
//    @FormUrlEncoded
//    @POST(ApiConstants.DELETE_FAVOURITE_ITEM)
//    Observable<DeleteModel> deleteFavItem(@Field("user_id") String user_id, @Field("favourite_id") String favourite_id);
//
//
//    @FormUrlEncoded
//    @POST(ApiConstants.CATEGORY_FILTER)
//    Observable<FilterCategoryModel> getFilterCategory(@Field("user_id") String user_id);
//
//
//    @FormUrlEncoded
//    @POST(ApiConstants.BRANDS_FILTER)
//    Observable<FilterBrandModel> getFilterBrand(@Field("user_id") String user_id);
//
//
//    //user_id,product_id,quantity,
//    @FormUrlEncoded
//    @POST(ApiConstants.ADD_CART)
//    Observable<SucessModel> addCart(@FieldMap HashMap<String, String> param);
//
//
//    @FormUrlEncoded
//    @POST(ApiConstants.GET_CART)
//    Observable<GetCartModel> getCart(@Field("user_id") String user_id);
//
//
//    @FormUrlEncoded
//    @POST(ApiConstants.REMOVE_ITEM)
//    Observable<SucessModel> removeCartItem(@Field("user_id") String user_id, @Field("cart_item_id") String cart_item_id, @Field("product_id") String product_id);
//
//    //user_id,cart_item_id,operation_status
//    @FormUrlEncoded
//    @POST(ApiConstants.UPDATE_CART_QUANTITY)
//    Observable<SucessModel> updateItemQuant(@Field("user_id") String user_id, @Field("cart_item_id") String cart_item_id, @Field("operation_status") String operation_status);
//
//    //user_id,address_type,address,state,city,zipcode,lat,lng
//
//    @FormUrlEncoded
//    @POST(ApiConstants.ADD_DELIVERY_ADDRESS)
//    Observable<SucessModel> addDeliveryAddress(@Field("user_id") String user_id, @Field("address_type") String address_type, @Field("address") String address, @Field("country") String state, @Field("city") String city, @Field("zipcode") String zipcode, @Field("lat") String lat, @Field("lng") String lng, @Field("name") String name, @Field("phone_number") String phone_number);
//
//
//    @FormUrlEncoded
//    @POST(ApiConstants.GET_DELIVERY_ADDRESS)
//    Observable<AddressBookModel> getDeliveryAddress(@Field("user_id") String user_id);
//
//
//    @FormUrlEncoded
//    @POST(ApiConstants.REMOVE_DELIVERY_ADDRESS)
//    Observable<SucessModel> removeAddress(@Field("user_id") String user_id, @Field("delivery_address_id") String delivery_address_id);
//
//
//    @FormUrlEncoded
//    @POST(ApiConstants.UPDATE_DELIVERY_ADDRESS)
//    Observable<SucessModel> updateDeliveryAddress(@Field("user_id") String user_id, @Field("address_type") String address_type, @Field("buyer_delivery_address_id") String buyer_delivery_address_id, @Field("address") String address, @Field("country") String state, @Field("city") String city, @Field("zipcode") String zipcode, @Field("lat") String lat, @Field("lng") String lng, @Field("name") String name, @Field("phone_number") String phone_number);
//
//
//    @FormUrlEncoded
//    @POST(ApiConstants.GET_PROMOTIONAL_CATEGORIES)
//    Observable<PromotionalCategoryModel> getPromoCategories(@Field("user_id") String user_id);
//
//    @FormUrlEncoded
//    @POST(ApiConstants.GET_PROMO_PRODUCT)
//    Observable<SucessModel> getPromoProduct(@Field("user_id") String user_id, @Field("category_id") String category_id);
//
//
//    @FormUrlEncoded
//    @POST(ApiConstants.REVIEW_CART)
//    Observable<CheckoutModel> reviewCart(@Field("user_id") String user_id);
//
//
//    @FormUrlEncoded
//    @POST(ApiConstants.APPLY_PROMOCODE)
//    Observable<ApplyPromoModel> applyPromo(@Field("user_id") String user_id, @Field("promo_code") String promo_code, @Field("cart_id") String cart_id);
//
//
//    @FormUrlEncoded
//    @POST(ApiConstants.REMOVE_PROMOCODE)
//    Observable<SucessModel> removePromo(@Field("user_id") String user_id, @Field("cart_id") String cart_id);
//
//    @FormUrlEncoded
//    @POST(ApiConstants.MAKE_DEFAULT_ADDRESS)
//    Observable<SucessModel> makeDefaultAddress(@Field("user_id") String user_id, @Field("address_id") String address_id);
//
////user_id,sub_total,delivery_charge,tax,delivery_address_id,promo_id,discount,payment_method,cart_ids,sizes
//    @FormUrlEncoded
//    @POST(ApiConstants.MAKE_ORDER)
//    Observable<MakeOrderModel> makeOrder(@Field("user_id") String user_id, @Field("sub_total") String sub_total, @Field("delivery_charge") String delivery_charge, @Field("tax") String tax, @Field("delivery_address_id") String delivery_address_id, @Field("promo_id") String promo_id, @Field("discount") String discount, @Field("payment_method") String payment_method, @Field("cart_ids") String cart_ids, @Field("final_total") String final_total);
//
//
//    @FormUrlEncoded
//    @POST(ApiConstants.ORDER_LISTING)
//    Observable<OrderListModel> orderListing(@Field("user_id") String user_id);
//
//
//    @FormUrlEncoded
//    @POST(ApiConstants.ORDER_DETAIL)
//    Observable<OrderDetailModel> orderDetail(@Field("user_id") String user_id, @Field("order_uid") String order_uid, @Field("product_id") String product_id);
//
//
//    //user_id, rating, review, order_uid,product_id
//    @FormUrlEncoded
//    @POST(ApiConstants.RATE_PRODUCT)
//    Observable<SucessModel> rateProduct(@Field("user_id") String user_id, @Field("rating") String rating, @Field("review") String review, @Field("order_uid") String order_uid, @Field("product_id") String product_id);
//
//
//    @FormUrlEncoded
//    @POST(ApiConstants.SEND_ENQUIRY)
//    Observable<SucessModel> sendAdminEnquiry(@Field("user_id") String user_id, @Field("title") String title);
//
//    @FormUrlEncoded
//    @POST(ApiConstants.GET_ENQUIRY_LIST)
//    Observable<EnquiryListModel> getEnquiryList(@Field("user_id") String user_id);
//
//    @FormUrlEncoded
//    @POST(ApiConstants.START_CONVERSATION)
//    Observable<GetMessageModel> startMessageConversation(@Field("user_id") String user_id, @Field("enquiry_id") String enquiry_id);
//
//    @FormUrlEncoded
//    @POST(ApiConstants.SEND_ENQUIRY_MESSAGE)
//    Observable<SendEnquiryMessage> sendEnquiryMessage(@Field("conversation_id") String conversation_id, @Field("user_from") String user_from, @Field("message") String message, @Field("sender_type") String sender_type);
//
//    @FormUrlEncoded
//    @POST(ApiConstants.GET_MESSAGE)
//    Observable<GetMessageModel> getLastMessageId(@Field("conversation_id") String conversation_id, @Field("last_msg_id") String last_msg_id);
//
//
//    @GET(ApiConstants.GENERATE_CLIENT_TOKEN)
//    Observable<GetClientToken> getClientToken();
//
//    //user_id,order_uid,payment_method_nonce,amount,type
//    @FormUrlEncoded
//    @POST(ApiConstants.MAKE_CUSTOMER_PAYMENT)
//    Observable<MakeOrderModel> updateOrderPayment(@Field("user_id") String user_id, @Field("order_uid") String order_uid, @Field("payment_method_nonce") String payment_method_nonce, @Field("amount") String amount, @Field("type") String type);
//
//
//    @FormUrlEncoded
//    @POST(ApiConstants.SEARCH_PRODUCT)
//    Observable<SearchProductModel> searchProduct(@Field("user_id") String user_id, @Field("title") String title);
//
//    @FormUrlEncoded
//    @POST(ApiConstants.CATEGORY_BRANDS)
//    Observable<HomeBrandListModel> getcategoryBrand(@Field("user_id") String user_id, @Field("gender_type") String gender_type, @Field("type") String type);
//
//
////rider_id, customer_id, order_uid, type
//    @FormUrlEncoded
//    @POST(ApiConstants.START_CONVERSATION_USER)
//    Observable<GetUserMessageModel> startconversationUser(@Field("receiver_id") String receiver_id, @Field("sender_id") String sender_id, @Field("order_uid") String order_uid, @Field("sender_type") String sender_type, @Field("message") String message, @Field("order_seller_id") String order_seller_id);
//
//
//    //conversation_id, user_from, user_to, message
//    @FormUrlEncoded
//    @POST(ApiConstants.SEND_MESSAGE_USER)
//    Observable<SucessModel> sendMessageUser(@Field("conversation_id") String conversation_id, @Field("user_from") String user_from, @Field("user_to") String user_to, @Field("message") String message);
//
//    //conversation_id, last_msg_id
//    @FormUrlEncoded
//    @POST(ApiConstants.GET_MESSSAGE_USER)
//    Observable<UserMessageModel> getMessageUser(@Field("conversation_id") String conversation_id, @Field("last_msg_id") String last_msg_id);
//
//
//    @FormUrlEncoded
//    @POST(ApiConstants.MESSAGE_LIST)
//    Observable<GetUserMessagesList> messageListUser(@Field("user_id") String user_id);
//
//
//    @FormUrlEncoded
//    @POST(ApiConstants.NOTIFICATION_LIST)
//    Observable<NotificationListModel> getNotificationList(@Field("user_id") String user_id);
//
//
//    //user_id,order_uid,product_id
//    @FormUrlEncoded
//    @POST(ApiConstants.CANCEL_ORDER)
//    Observable<SucessModel> cancelOrder(@Field("user_id") String user_id, @Field("order_uid") String order_uid, @Field("product_id") String product_id);
//
//
//    @FormUrlEncoded
//    @POST(ApiConstants.LOGOUT)
//    Observable<SucessModel> logout(@Field("user_id") String user_id);
//
//
//    //seller_id,star,comment,user_id,username
//    @FormUrlEncoded
//    @POST(ApiConstants.RATE_SELLER)
//    Observable<SucessModel> rateSeller(@Field("seller_id") String seller_id, @Field("star") String star, @Field("comment") String comment, @Field("user_id") String user_id, @Field("order_uid") String order_uid);
//
//    @FormUrlEncoded
//    @POST(ApiConstants.RATE_RIDER)
//    Observable<SucessModel> rateRider(@Field("rider_id") String rider_id, @Field("star") String star, @Field("comment") String comment, @Field("user_id") String user_id, @Field("task_id") String task_id);
//
//
//    @FormUrlEncoded
//    @POST(ApiConstants.GET_MESSAGE_DETAIL)
//    Observable<UserMessageModel> getMessageDetail(@Field("user_id") String user_id, @Field("conversation_id") String conversation_id);
//
//
//    @GET("distancematrix/json?") // origins/destinations:  LatLng as string
//    Observable<DistanceMatrixModel> getDistance(@Query(value = "units", encoded = true) String type, @Query("key") String key, @Query("origins") String origins, @Query("destinations") String destinations);

}


