export abstract class RequestMapping{
  private static commonUrl = "api/v1/";

  //auth
  static register = RequestMapping.commonUrl + "auth/register";
  static login = RequestMapping.commonUrl + "auth/login";
  static logout = RequestMapping.commonUrl + "auth/logout";

  //user info
  static getCurrentUser = RequestMapping.commonUrl + "user/info";

  //catalog
  static getCatalogs = RequestMapping.commonUrl + "catalog/category/all";

  //product
  static getProducts = RequestMapping.commonUrl + "product/sub/{sub_id}/";

  //user
  static addToBasket = RequestMapping.commonUrl + "me/add-to-basket?id={id}";
  static getBasket = RequestMapping.commonUrl + "me/get-basket";

}
