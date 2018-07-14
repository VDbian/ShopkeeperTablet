package com.administrator.shopkeepertablet.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Description:
 * Author CC
 * Time 2018/7/13
 */


public class ComboEntity {
    /**
     * PRODUCTID : e209718f-45da-4af2-b51f-7aacef7d29d6
     * Type : true
     * Price : 998.0
     * PackageName : 包席
     * counts : 1,1,1
     * PRODUCTNAME : 查查,清炒花菜,生椒牛肉粉
     * Guid : e209718f-45da-4af2-b51f-7aacef7d29d6
     * PRODUCTTYPEID : c94358c3-d8c4-426a-a484-57bc2dbada20
     * RESTAURANTID : 4B176F0E-0553-4094-8181-5048641B20EF
     * MemberPice : 888.0
     * ProtuctShuXing : 0
     * PRODUCTFile :
     */
    @SerializedName("PRODUCTID")
    private String productId;
    @SerializedName("Type")
    private boolean type;
    @SerializedName("Price")
    private double price;
    @SerializedName("PackageName")
    private String packageName;
    @SerializedName("counts")
    private String counts;
    @SerializedName("PRODUCTNAME")
    private String productName;
    @SerializedName("Guid")
    private String guid;
    @SerializedName("PRODUCTTYPEID")
    private String productTypeId;
    @SerializedName("RESTAURANTID")
    private String restaurantId;
    @SerializedName("MemberPice")
    private double memberPrice;
    @SerializedName("ProtuctShuXing")
    private String productShuXing;
    @SerializedName("PRODUCTFile")
    private String productFile;

}
