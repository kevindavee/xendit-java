package com.xendit.model;

import com.xendit.Xendit;
import com.xendit.exception.XenditException;
import com.xendit.network.RequestResource;
import java.util.HashMap;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CreditCard {
  /**
   * Create authorization with given parameters
   *
   * @param tokenId The token ID used to charge the card.
   * @param externalId A unique identifier of your choice. Max 64 characters.
   * @param amount The charge amount
   * @param authenticationId Authentication ID for authenticating charge. Optional only if charge
   *     was already authenticated with a single-use token, or if optional authentication is enabled
   *     for your account.
   * @param cardCVN 3 or 4 digit CVN (CVC) code. Optional but highly recommended. Required for cards
   *     issued in Europe.
   * @param capture Whether or not to capture immediately.
   * @return CreditCardCharge
   * @throws XenditException XenditException
   */
  public static CreditCardCharge createAuthorization(
      String tokenId,
      String externalId,
      Number amount,
      String authenticationId,
      String cardCVN,
      Boolean capture)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("token_id", tokenId);
    params.put("external_id", externalId);
    params.put("amount", amount);
    params.put("authentication_id", authenticationId);
    params.put("card_cvn", cardCVN);
    params.put("capture", capture);
    String url = String.format("%s%s", Xendit.getUrl(), "/credit_card_charges");

    return Xendit.requestClient.request(
        RequestResource.Method.POST, url, params, CreditCardCharge.class);
  }

  /**
   * Create authorization with parameters in a HashMap
   *
   * @param params listed here https://xendit.github.io/apireference/#create-charge
   * @return CreditCardCharge
   * @throws XenditException XenditException
   */
  public static CreditCardCharge createAuthorization(Map<String, Object> params)
      throws XenditException {
    String url = String.format("%s%s", Xendit.getUrl(), "/credit_card_charges");
    return Xendit.requestClient.request(
        RequestResource.Method.POST, url, params, CreditCardCharge.class);
  }

  /**
   * Create charge with given parameters
   *
   * @param tokenId The token ID used to charge the card.
   * @param externalId A unique identifier of your choice. Max 64 characters.
   * @param amount The charge amount
   * @param authenticationId Authentication ID for authenticating charge. Optional only if charge
   *     was already authenticated with a single-use token, or if optional authentication is enabled
   *     for your account.
   * @param cardCVN 3 or 4 digit CVN (CVC) code. Optional but highly recommended. Required for cards
   *     issued in Europe.
   * @param descriptor Spesific descriptor to define merchant's identity
   * @return CreditCardCharge
   * @throws XenditException XenditException
   */
  public static CreditCardCharge createCharge(
      String tokenId,
      String externalId,
      Number amount,
      String authenticationId,
      String cardCVN,
      String descriptor)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("token_id", tokenId);
    params.put("external_id", externalId);
    params.put("amount", amount);
    params.put("authentication_id", authenticationId);
    params.put("card_cvn", cardCVN);
    params.put("descriptor", descriptor);
    String url = String.format("%s%s", Xendit.getUrl(), "/credit_card_charges");

    return Xendit.requestClient.request(
        RequestResource.Method.POST, url, params, CreditCardCharge.class);
  }

  /**
   * Create charge with parameters in a HashMap
   *
   * @param params listed here https://xendit.github.io/apireference/#create-charge
   * @return CreditCardCharge
   * @throws XenditException XenditException
   */
  public static CreditCardCharge createCharge(Map<String, Object> params) throws XenditException {
    String url = String.format("%s%s", Xendit.getUrl(), "/credit_card_charges");
    return Xendit.requestClient.request(
        RequestResource.Method.POST, url, params, CreditCardCharge.class);
  }

  /**
   * Reverse authorization by external ID
   *
   * @param externalId Charge reference
   * @return CreditCardReverseAuth
   * @throws XenditException XenditException
   */
  public static CreditCardReverseAuth reverseAuthorization(String chargeId, String externalId)
      throws XenditException {
    String url =
        String.format(
            "%s%s%s%s", Xendit.getUrl(), "/credit_card_charges/", chargeId, "/auth_reversal");
    Map<String, Object> params = new HashMap<>();
    params.put("external_id", externalId);

    return Xendit.requestClient.request(
        RequestResource.Method.POST, url, params, CreditCardReverseAuth.class);
  }

  /**
   * Capture a charge by charge ID
   *
   * @param chargeId Charge ID of authorization
   * @param amount Amount to be captured. Can be up to amount of authorization but not more
   * @return CreditCardCharge
   * @throws XenditException XenditException
   */
  public static CreditCardCharge captureCharge(String chargeId, Number amount)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("amount", amount);
    String url =
        String.format("%s%s%s%s", Xendit.getUrl(), "/credit_card_charges/", chargeId, "/capture");

    return Xendit.requestClient.request(
        RequestResource.Method.POST, url, params, CreditCardCharge.class);
  }

  /**
   * Get a charge by ID
   *
   * @param id Charge ID of the payment that have been authorized
   * @return CreditCardCharge
   * @throws XenditException XenditException
   */
  public static CreditCardCharge getCharge(String id) throws XenditException {
    String url = String.format("%s%s%s", Xendit.getUrl(), "/credit_card_charges/", id);
    return Xendit.requestClient.request(
        RequestResource.Method.GET, url, null, CreditCardCharge.class);
  }

  /**
   * Create a refund
   *
   * @param id Charge ID of the payment that will be refunded
   * @param amount The amount to be refunded
   * @param externalId A unique identifier of your choice. Max 64 characters.
   * @return CreditCardRefund
   * @throws XenditException XenditException
   */
  public static CreditCardRefund createRefund(String id, Number amount, String externalId)
      throws XenditException {
    Map<String, Object> params = new HashMap<>();
    params.put("amount", amount);
    params.put("external_id", externalId);
    String url =
        String.format("%s%s%s%s", Xendit.getUrl(), "/credit_card_charges/", id, "/refunds");

    return Xendit.requestClient.request(
        RequestResource.Method.POST, url, params, CreditCardRefund.class);
  }
}
