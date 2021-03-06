/*
 * Zuora API Reference
 *  # Introduction  Welcome to the reference for the Zuora REST API!  <a href=\"http://en.wikipedia.org/wiki/REST_API\" target=\"_blank\">REST</a> is a web-service protocol that lends itself to rapid development by using everyday HTTP and JSON technology.  The Zuora REST API provides a broad set of operations and resources that:  * Enable Web Storefront integration between your websites. * Support self-service subscriber sign-ups and account management. * Process revenue schedules through custom revenue rule models.  ## Endpoints  The Zuora REST services are provided via the following endpoints.  | Service                 | Base URL for REST Endpoints                                                                                                                                         | |-------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------| | Production REST service | https://rest.zuora.com/v1                                                                                                                                           | | Sandbox REST service    | https://rest.apisandbox.zuora.com/v1                                                                                                                                |  The production service provides access to your live user data. The sandbox environment is a good place to test your code without affecting real-world data. To use it, you must be provisioned with a sandbox tenant - your Zuora representative can help with this if needed.  ## Accessing the API  If you have a Zuora tenant, you already have access the the API.  If you don't have a Zuora tenant, go to <a href=\"https://www.zuora.com/resource/zuora-test-drive\" target=\"_blank\">https://www.zuora.com/resource/zuora-test-drive</a> and sign up for a trial tenant. The tenant comes with seed data, such as a sample product catalog.   We recommend that you <a href=\"https://knowledgecenter.zuora.com/CF_Users_and_Administrators/A_Administrator_Settings/Manage_Users/Create_an_API_User\" target=\"_blank\">create an API user</a> specifically for making API calls. Don't log in to the Zuora UI with this account. Logging in to the UI enables a security feature that periodically expires the account's password, which may eventually cause authentication failures with the API. Note that a user role does not have write access to Zuora REST services unless it has the API Write Access permission as described in those instructions.   # Authentication  There are three ways to authenticate:  * Use an authorization cookie. The cookie authorizes the user to make calls to the REST API for the duration specified in  **Administration > Security Policies > Session timeout**. The cookie expiration time is reset with this duration after every call to the REST API. To obtain a cookie, call the REST  `connections` resource with the following API user information:     *   ID     *   password     *   entity Id or entity name (Only for [Zuora Multi-entity](https://knowledgecenter.zuora.com/BB_Introducing_Z_Business/Multi-entity \"Multi-entity\"). See \"Entity Id and Entity Name\" below for more information.)  *   Include the following parameters in the request header, which re-authenticates the user with each request:     *   `apiAccessKeyId`     *   `apiSecretAccessKey`     *   `entityId` or `entityName` (Only for [Zuora Multi-entity](https://knowledgecenter.zuora.com/BB_Introducing_Z_Business/Multi-entity \"Multi-entity\"). See \"Entity Id and Entity Name\" below for more information.) *   For CORS-enabled APIs only: Include a 'single-use' token in the request header, which re-authenticates the user with each request. See below for more details.   ## Entity Id and Entity Name  The `entityId` and `entityName`  parameters are only used for  [Zuora Multi-entity](https://knowledgecenter.zuora.com/BB_Introducing_Z_Business/Multi-entity).  The  `entityId` parameter specifies the Id of the entity that you want to access. The `entityName` parameter specifies the [name of the entity](https://knowledgecenter.zuora.com/BB_Introducing_Z_Business/Multi-entity/B_Introduction_to_Entity_and_Entity_Hierarchy#Name_and_Display_Name \"Introduction to Entity and Entity Hierarchy\") that you want to access. Note that you must have permission to access the entity. You can get the entity Id and entity name through the REST GET Entities call.  You can specify either the  `entityId` or `entityName` parameter in the authentication to access and view an entity.  *   If both `entityId` and `entityName` are specified in the authentication, an error occurs.  *   If neither  `entityId` nor  `entityName` is specified in the authentication, you will log in to the entity in which your user account is created.   See [API User Authentication](https://knowledgecenter.zuora.com/BB_Introducing_Z_Business/Multi-entity/A_Overview_of_Multi-entity#API_User_Authentication \"Zuora Multi-entity\") for more information.  ## Token Authentication for CORS-Enabled APIs  The CORS mechanism enables REST API calls to Zuora to be made directly from your customer's browser, with all credit card and security information transmitted directly to Zuora. This minimizes your PCI compliance burden, allows you to implement advanced validation on your payment forms, and makes your payment forms look just like any other part of your website.  For security reasons, instead of using cookies, an API request via CORS uses **tokens** for authentication.  The token method of authentication is only designed for use with requests that must originate from your customer's browser; **it should not be considered a replacement to the existing cookie authentication** mechanism.  See [Zuora CORS REST ](https://knowledgecenter.zuora.com/DC_Developers/REST_API/A_REST_basics/G_CORS_REST \"Zuora CORS REST\")for details on how CORS works and how you can begin to implement customer calls to the Zuora REST APIs. See  [HMAC Signatures](/BC_Developers/REST_API/B_REST_API_reference/HMAC_Signatures \"HMAC Signatures\") for details on the HMAC method that returns the authentication token.    # Requests and Responses   ## Request IDs  As a general rule, when asked to supply a \"key\" for an account or subscription (accountKey, account-key, subscriptionKey, subscription-key), you can provide either the actual ID or the number of the entity.  ## HTTP Request Body  Most of the parameters and data accompanying your requests will be contained in the body of the HTTP request.  The Zuora REST API accepts JSON in the HTTP request body.  No other data format (e.g., XML) is supported.   ## Testing a Request  Use a third party client, such as Postman or Advanced REST Client, to test the Zuora REST API.  You can test the Zuora REST API from the Zuora sandbox or  production service. If connecting to the production service, bear in mind that you are working with your live production data, not sample data or test data.  ## Testing with Credit Cards  Sooner or later it will probably be necessary to test some transactions that involve credit cards. For suggestions on how to handle this, see [Going Live With Your Payment Gateway](https://knowledgecenter.zuora.com/CB_Billing/M_Payment_Gateways/C_Managing_Payment_Gateways/B_Going_Live_Payment_Gateways#Testing_with_Credit_Cards \"C_Zuora_User_Guides/A_Billing_and_Payments/M_Payment_Gateways/C_Managing_Payment_Gateways/B_Going_Live_Payment_Gateways#Testing_with_Credit_Cards\").       ## Error Handling  Responses and error codes are detailed in [Responses and errors](https://knowledgecenter.zuora.com/DC_Developers/REST_API/A_REST_basics/3_Responses_and_errors \"Responses and errors\").    # Pagination  When retrieving information (using GET methods), the optional `pageSize` query parameter sets the maximum number of rows to return in a response. The maximum is `40`; larger values are treated as `40`. If this value is empty or invalid, `pageSize` typically defaults to `10`.  The default value for the maximum number of rows retrieved can be overridden at the method level.  If more rows are available, the response will include a `nextPage` element, which contains a URL for requesting the next page.  If this value is not provided, no more rows are available. No \"previous page\" element is explicitly provided; to support backward paging, use the previous call.  ## Array Size  For data items that are not paginated, the REST API supports arrays of up to 300 rows.  Thus, for instance, repeated pagination can retrieve thousands of customer accounts, but within any account an array of no more than 300 rate plans is returned.   # API Versions  The Zuora REST API is in version control. Versioning ensures that Zuora REST API changes are backward compatible. Zuora uses a major and minor version nomenclature to manage changes. By specifying a version in a REST request, you can get expected responses regardless of future changes to the API.   ## Major Version  The major version number of the REST API appears in the REST URL. Currently, Zuora only supports the **v1** major version. For example,  `POST https://rest.zuora.com/v1/subscriptions` .   ## Minor Version  Zuora uses minor versions for the REST API to control small changes. For example, a field in a REST method is deprecated and a new field is used to replace it.   Some fields in the REST methods are supported as of minor versions. If a field is not noted with a minor version, this field is available for all minor versions. If a field is noted with a minor version, this field is in version control. You must specify the supported minor version in the request header to process without an error.   If a field is in version control, it is either with a minimum minor version or a maximum minor version, or both of them. You can only use this field with the minor version between the minimum and the maximum minor versions. For example, the  `invoiceCollect` field in the POST Subscription method is in version control and its maximum minor version is 189.0. You can only use this field with the minor version 189.0 or earlier.  The supported minor versions are not serial, see [Zuora REST API Minor Version History](https://knowledgecenter.zuora.com/DC_Developers/REST_API/A_REST_basics/Zuora_REST_API_Minor_Version_History \"Zuora REST API Minor Version History\") for the fields and their supported minor versions. In our REST API documentation, if a field or feature requires a minor version number, we note that in the field description.  You only need to specify the version number when you use the fields require a minor version. To specify the minor version, set the `zuora-version` parameter to the minor version number in the request header for the request call. For example, the `collect` field is in 196.0 minor version. If you want to use this field for the POST Subscription method, set the  `zuora-version` parameter to `196.0` in the request header. The `zuora-version` parameter is case sensitive.   For all the REST API fields, by default, if the minor version is not specified in the request header, Zuora will use the minimum minor version of the REST API to avoid breaking your integration.     # Zuora Object Model The following diagram presents a high-level view of the key Zuora objects. Click the image to open it in a new tab to resize it.  <a href=\"https://www.zuora.com/wp-content/uploads/2016/10/ZuoraERD-compressor-1.jpeg\" target=\"_blank\"><img src=\"https://www.zuora.com/wp-content/uploads/2016/10/ZuoraERD-compressor-1.jpeg\" alt=\"Zuora Object Model Diagram\"></a> 
 *
 * OpenAPI spec version: 0.0.1
 * Contact: docs@zuora.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package io.swagger.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.client.model.PUTSrpAddType;
import io.swagger.client.model.PUTSrpRemoveType;
import io.swagger.client.model.PUTSrpUpdateType;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.LocalDate;


/**
 * PUTSubscriptionType
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-10-31T11:45:12.183-07:00")
public class PUTSubscriptionType   {
  @SerializedName("CpqBundleJsonId__QT")
  private String cpqBundleJsonIdQT = null;

  @SerializedName("OpportunityCloseDate__QT")
  private String opportunityCloseDateQT = null;

  @SerializedName("OpportunityName__QT")
  private String opportunityNameQT = null;

  @SerializedName("QuoteBusinessType__QT")
  private String quoteBusinessTypeQT = null;

  @SerializedName("QuoteNumber__QT")
  private String quoteNumberQT = null;

  @SerializedName("QuoteType__QT")
  private String quoteTypeQT = null;

  @SerializedName("add")
  private List<PUTSrpAddType> add = new ArrayList<PUTSrpAddType>();

  @SerializedName("applyCreditBalance")
  private Boolean applyCreditBalance = null;

  @SerializedName("autoRenew")
  private Boolean autoRenew = null;

  @SerializedName("currentTerm")
  private Long currentTerm = null;

  @SerializedName("currentTermPeriodType")
  private String currentTermPeriodType = null;

  @SerializedName("customField__c")
  private String customFieldC = null;

  @SerializedName("includeExistingDraftInvoiceItems")
  private Boolean includeExistingDraftInvoiceItems = null;

  @SerializedName("invoice")
  private Boolean invoice = null;

  @SerializedName("invoiceCollect")
  private Boolean invoiceCollect = null;

  @SerializedName("invoiceSeparately")
  private Boolean invoiceSeparately = null;

  @SerializedName("invoiceTargetDate")
  private LocalDate invoiceTargetDate = null;

  @SerializedName("notes")
  private String notes = null;

  @SerializedName("preview")
  private Boolean preview = null;

  @SerializedName("previewType")
  private String previewType = null;

  @SerializedName("remove")
  private List<PUTSrpRemoveType> remove = new ArrayList<PUTSrpRemoveType>();

  @SerializedName("renewalSetting")
  private String renewalSetting = null;

  @SerializedName("renewalTerm")
  private Long renewalTerm = null;

  @SerializedName("renewalTermPeriodType")
  private String renewalTermPeriodType = null;

  @SerializedName("termStartDate")
  private LocalDate termStartDate = null;

  @SerializedName("termType")
  private String termType = null;

  @SerializedName("update")
  private List<PUTSrpUpdateType> update = new ArrayList<PUTSrpUpdateType>();

  public PUTSubscriptionType cpqBundleJsonIdQT(String cpqBundleJsonIdQT) {
    this.cpqBundleJsonIdQT = cpqBundleJsonIdQT;
    return this;
  }

   /**
   * 
   * @return cpqBundleJsonIdQT
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getCpqBundleJsonIdQT() {
    return cpqBundleJsonIdQT;
  }

  public void setCpqBundleJsonIdQT(String cpqBundleJsonIdQT) {
    this.cpqBundleJsonIdQT = cpqBundleJsonIdQT;
  }

  public PUTSubscriptionType opportunityCloseDateQT(String opportunityCloseDateQT) {
    this.opportunityCloseDateQT = opportunityCloseDateQT;
    return this;
  }

   /**
   * The closing date of the Opportunity. This field is populated when the subscription originates from Zuora Quotes.  This field is used only for reporting subscription metrics.   See [Subscription Data Source](https://knowledgecenter.zuora.com/CD_Reporting/Data_Exports/Z_Data_Source_Reference/Subscription_Data_Source) for more information. 
   * @return opportunityCloseDateQT
  **/
  @ApiModelProperty(example = "null", value = "The closing date of the Opportunity. This field is populated when the subscription originates from Zuora Quotes.  This field is used only for reporting subscription metrics.   See [Subscription Data Source](https://knowledgecenter.zuora.com/CD_Reporting/Data_Exports/Z_Data_Source_Reference/Subscription_Data_Source) for more information. ")
  public String getOpportunityCloseDateQT() {
    return opportunityCloseDateQT;
  }

  public void setOpportunityCloseDateQT(String opportunityCloseDateQT) {
    this.opportunityCloseDateQT = opportunityCloseDateQT;
  }

  public PUTSubscriptionType opportunityNameQT(String opportunityNameQT) {
    this.opportunityNameQT = opportunityNameQT;
    return this;
  }

   /**
   * The unique identifier of the Opportunity. This field is populated when the subscription originates from Zuora Quotes.  This field is used only for reporting subscription metrics.   See [Subscription Data Source](https://knowledgecenter.zuora.com/CD_Reporting/Data_Exports/Z_Data_Source_Reference/Subscription_Data_Source) for more information. 
   * @return opportunityNameQT
  **/
  @ApiModelProperty(example = "null", value = "The unique identifier of the Opportunity. This field is populated when the subscription originates from Zuora Quotes.  This field is used only for reporting subscription metrics.   See [Subscription Data Source](https://knowledgecenter.zuora.com/CD_Reporting/Data_Exports/Z_Data_Source_Reference/Subscription_Data_Source) for more information. ")
  public String getOpportunityNameQT() {
    return opportunityNameQT;
  }

  public void setOpportunityNameQT(String opportunityNameQT) {
    this.opportunityNameQT = opportunityNameQT;
  }

  public PUTSubscriptionType quoteBusinessTypeQT(String quoteBusinessTypeQT) {
    this.quoteBusinessTypeQT = quoteBusinessTypeQT;
    return this;
  }

   /**
   * The specific identifier for the type of business transaction the Quote represents such as `New`, `Upsell`, `Downsell`, `Renewal`, or `Churn`. This field is populated when the subscription originates from Zuora Quotes.            This field is used only for reporting subscription metrics.   See [Subscription Data Source](https://knowledgecenter.zuora.com/CD_Reporting/Data_Exports/Z_Data_Source_Reference/Subscription_Data_Source) for more information. 
   * @return quoteBusinessTypeQT
  **/
  @ApiModelProperty(example = "null", value = "The specific identifier for the type of business transaction the Quote represents such as `New`, `Upsell`, `Downsell`, `Renewal`, or `Churn`. This field is populated when the subscription originates from Zuora Quotes.            This field is used only for reporting subscription metrics.   See [Subscription Data Source](https://knowledgecenter.zuora.com/CD_Reporting/Data_Exports/Z_Data_Source_Reference/Subscription_Data_Source) for more information. ")
  public String getQuoteBusinessTypeQT() {
    return quoteBusinessTypeQT;
  }

  public void setQuoteBusinessTypeQT(String quoteBusinessTypeQT) {
    this.quoteBusinessTypeQT = quoteBusinessTypeQT;
  }

  public PUTSubscriptionType quoteNumberQT(String quoteNumberQT) {
    this.quoteNumberQT = quoteNumberQT;
    return this;
  }

   /**
   * The unique identifier of the Quote. This field is populated when the subscription originates from Zuora Quotes.            This field is used only for reporting subscription metrics.   See [Subscription Data Source](https://knowledgecenter.zuora.com/CD_Reporting/Data_Exports/Z_Data_Source_Reference/Subscription_Data_Source) for more information. 
   * @return quoteNumberQT
  **/
  @ApiModelProperty(example = "null", value = "The unique identifier of the Quote. This field is populated when the subscription originates from Zuora Quotes.            This field is used only for reporting subscription metrics.   See [Subscription Data Source](https://knowledgecenter.zuora.com/CD_Reporting/Data_Exports/Z_Data_Source_Reference/Subscription_Data_Source) for more information. ")
  public String getQuoteNumberQT() {
    return quoteNumberQT;
  }

  public void setQuoteNumberQT(String quoteNumberQT) {
    this.quoteNumberQT = quoteNumberQT;
  }

  public PUTSubscriptionType quoteTypeQT(String quoteTypeQT) {
    this.quoteTypeQT = quoteTypeQT;
    return this;
  }

   /**
   * The Quote type that represents the subscription lifecycle stage such as `New`, `Amendment`, `Renew`, or `Cancel`. This field is populated when the subscription originates from Zuora Quotes.            This field is used only for reporting subscription metrics.   See [Subscription Data Source](https://knowledgecenter.zuora.com/CD_Reporting/Data_Exports/Z_Data_Source_Reference/Subscription_Data_Source) for more information. 
   * @return quoteTypeQT
  **/
  @ApiModelProperty(example = "null", value = "The Quote type that represents the subscription lifecycle stage such as `New`, `Amendment`, `Renew`, or `Cancel`. This field is populated when the subscription originates from Zuora Quotes.            This field is used only for reporting subscription metrics.   See [Subscription Data Source](https://knowledgecenter.zuora.com/CD_Reporting/Data_Exports/Z_Data_Source_Reference/Subscription_Data_Source) for more information. ")
  public String getQuoteTypeQT() {
    return quoteTypeQT;
  }

  public void setQuoteTypeQT(String quoteTypeQT) {
    this.quoteTypeQT = quoteTypeQT;
  }

  public PUTSubscriptionType add(List<PUTSrpAddType> add) {
    this.add = add;
    return this;
  }

  public PUTSubscriptionType addAddItem(PUTSrpAddType addItem) {
    this.add.add(addItem);
    return this;
  }

   /**
   * Container for adding one or more rate plans. 
   * @return add
  **/
  @ApiModelProperty(example = "null", value = "Container for adding one or more rate plans. ")
  public List<PUTSrpAddType> getAdd() {
    return add;
  }

  public void setAdd(List<PUTSrpAddType> add) {
    this.add = add;
  }

  public PUTSubscriptionType applyCreditBalance(Boolean applyCreditBalance) {
    this.applyCreditBalance = applyCreditBalance;
    return this;
  }

   /**
   * Applies a credit balance to an invoice.  If the value is `true`, the credit balance is applied to the invoice. If the value is `false`, no action is taken.  **Prerequisite:** `invoice` must be true.   **Note:** If you are using the field `invoiceCollect` rather than the field `invoice`, the `invoiceCollect` value must be true.  To view the credit balance adjustment, retrieve the details of the invoice using the Get Invoices method. 
   * @return applyCreditBalance
  **/
  @ApiModelProperty(example = "null", value = "Applies a credit balance to an invoice.  If the value is `true`, the credit balance is applied to the invoice. If the value is `false`, no action is taken.  **Prerequisite:** `invoice` must be true.   **Note:** If you are using the field `invoiceCollect` rather than the field `invoice`, the `invoiceCollect` value must be true.  To view the credit balance adjustment, retrieve the details of the invoice using the Get Invoices method. ")
  public Boolean getApplyCreditBalance() {
    return applyCreditBalance;
  }

  public void setApplyCreditBalance(Boolean applyCreditBalance) {
    this.applyCreditBalance = applyCreditBalance;
  }

  public PUTSubscriptionType autoRenew(Boolean autoRenew) {
    this.autoRenew = autoRenew;
    return this;
  }

   /**
   * If `true`, this subscription automatically renews at the end of the subscription term. Default is `false`. 
   * @return autoRenew
  **/
  @ApiModelProperty(example = "null", value = "If `true`, this subscription automatically renews at the end of the subscription term. Default is `false`. ")
  public Boolean getAutoRenew() {
    return autoRenew;
  }

  public void setAutoRenew(Boolean autoRenew) {
    this.autoRenew = autoRenew;
  }

  public PUTSubscriptionType currentTerm(Long currentTerm) {
    this.currentTerm = currentTerm;
    return this;
  }

   /**
   * The length of the period for the current subscription term. If `termType` is `TERMED`, this field is required and must be greater than `0`. If `termType` is `EVERGREEN`, this value is ignored. Default is `0`. 
   * @return currentTerm
  **/
  @ApiModelProperty(example = "null", value = "The length of the period for the current subscription term. If `termType` is `TERMED`, this field is required and must be greater than `0`. If `termType` is `EVERGREEN`, this value is ignored. Default is `0`. ")
  public Long getCurrentTerm() {
    return currentTerm;
  }

  public void setCurrentTerm(Long currentTerm) {
    this.currentTerm = currentTerm;
  }

  public PUTSubscriptionType currentTermPeriodType(String currentTermPeriodType) {
    this.currentTermPeriodType = currentTermPeriodType;
    return this;
  }

   /**
   * The period type for the current subscription term.  This field is used with the `CurrentTerm` field to specify the current subscription term.  Values are:  * `Month` (default) * `Year` * `Day` * `Week` 
   * @return currentTermPeriodType
  **/
  @ApiModelProperty(example = "null", value = "The period type for the current subscription term.  This field is used with the `CurrentTerm` field to specify the current subscription term.  Values are:  * `Month` (default) * `Year` * `Day` * `Week` ")
  public String getCurrentTermPeriodType() {
    return currentTermPeriodType;
  }

  public void setCurrentTermPeriodType(String currentTermPeriodType) {
    this.currentTermPeriodType = currentTermPeriodType;
  }

  public PUTSubscriptionType customFieldC(String customFieldC) {
    this.customFieldC = customFieldC;
    return this;
  }

   /**
   * Any custom fields defined for this object. 
   * @return customFieldC
  **/
  @ApiModelProperty(example = "null", value = "Any custom fields defined for this object. ")
  public String getCustomFieldC() {
    return customFieldC;
  }

  public void setCustomFieldC(String customFieldC) {
    this.customFieldC = customFieldC;
  }

  public PUTSubscriptionType includeExistingDraftInvoiceItems(Boolean includeExistingDraftInvoiceItems) {
    this.includeExistingDraftInvoiceItems = includeExistingDraftInvoiceItems;
    return this;
  }

   /**
   * Specifies whether to include draft invoice items in amendment previews.  Values:   * `true` (default). Includes draft invoice items in amendment previews.  * `false`. Excludes draft invoice items in amendment previews. 
   * @return includeExistingDraftInvoiceItems
  **/
  @ApiModelProperty(example = "null", value = "Specifies whether to include draft invoice items in amendment previews.  Values:   * `true` (default). Includes draft invoice items in amendment previews.  * `false`. Excludes draft invoice items in amendment previews. ")
  public Boolean getIncludeExistingDraftInvoiceItems() {
    return includeExistingDraftInvoiceItems;
  }

  public void setIncludeExistingDraftInvoiceItems(Boolean includeExistingDraftInvoiceItems) {
    this.includeExistingDraftInvoiceItems = includeExistingDraftInvoiceItems;
  }

  public PUTSubscriptionType invoice(Boolean invoice) {
    this.invoice = invoice;
    return this;
  }

   /**
   * Creates an invoice for a subscription. The invoice generated in this operation is only for this subscription, not for the entire customer account.  If the value is `true`, an invoice is created. If the value is `false`, no action is taken.  The default value is `false`.   This field is in Zuora REST API version control. Supported minor versions are 196.0 or later. To use this field in the method, you must set the `zuora-version` parameter to the minor version number in the request header. See [Zuora REST API Versions](https://knowledgecenter.zuora.com/DC_Developers/REST_API/A_REST_basics#Zuora_REST_API_Versions) for more information. 
   * @return invoice
  **/
  @ApiModelProperty(example = "null", value = "Creates an invoice for a subscription. The invoice generated in this operation is only for this subscription, not for the entire customer account.  If the value is `true`, an invoice is created. If the value is `false`, no action is taken.  The default value is `false`.   This field is in Zuora REST API version control. Supported minor versions are 196.0 or later. To use this field in the method, you must set the `zuora-version` parameter to the minor version number in the request header. See [Zuora REST API Versions](https://knowledgecenter.zuora.com/DC_Developers/REST_API/A_REST_basics#Zuora_REST_API_Versions) for more information. ")
  public Boolean getInvoice() {
    return invoice;
  }

  public void setInvoice(Boolean invoice) {
    this.invoice = invoice;
  }

  public PUTSubscriptionType invoiceCollect(Boolean invoiceCollect) {
    this.invoiceCollect = invoiceCollect;
    return this;
  }

   /**
   * **Note:** This field has been replaced by the `invoice` field and the `collect` field. `invoiceCollect` is available only for backward compatibility.  If `true`, an invoice is generated and payment collected automatically during the subscription process. If `false` (default), no invoicing or payment takes place.  The invoice generated in this operation is only for this subscription, not for the entire customer account.  This field is in Zuora REST API version control. Supported minor versions are 186.0, 187.0, 188.0, 189.0, and 196.0. See [Zuora REST API Versions](https://knowledgecenter.zuora.com/DC_Developers/REST_API/A_REST_basics#Zuora_REST_API_Versions) for more information. 
   * @return invoiceCollect
  **/
  @ApiModelProperty(example = "null", value = "**Note:** This field has been replaced by the `invoice` field and the `collect` field. `invoiceCollect` is available only for backward compatibility.  If `true`, an invoice is generated and payment collected automatically during the subscription process. If `false` (default), no invoicing or payment takes place.  The invoice generated in this operation is only for this subscription, not for the entire customer account.  This field is in Zuora REST API version control. Supported minor versions are 186.0, 187.0, 188.0, 189.0, and 196.0. See [Zuora REST API Versions](https://knowledgecenter.zuora.com/DC_Developers/REST_API/A_REST_basics#Zuora_REST_API_Versions) for more information. ")
  public Boolean getInvoiceCollect() {
    return invoiceCollect;
  }

  public void setInvoiceCollect(Boolean invoiceCollect) {
    this.invoiceCollect = invoiceCollect;
  }

  public PUTSubscriptionType invoiceSeparately(Boolean invoiceSeparately) {
    this.invoiceSeparately = invoiceSeparately;
    return this;
  }

   /**
   * Separates a single subscription from other subscriptions and invoices the charge independently.   If the value is `true`, the subscription is billed separately from other subscriptions. If the value is `false`, the subscription is included with other subscriptions in the account invoice.  The default value is `false`. Prerequisite: The default subscription setting Enable Subscriptions to be Invoiced Separately must be set to Yes. 
   * @return invoiceSeparately
  **/
  @ApiModelProperty(example = "null", value = "Separates a single subscription from other subscriptions and invoices the charge independently.   If the value is `true`, the subscription is billed separately from other subscriptions. If the value is `false`, the subscription is included with other subscriptions in the account invoice.  The default value is `false`. Prerequisite: The default subscription setting Enable Subscriptions to be Invoiced Separately must be set to Yes. ")
  public Boolean getInvoiceSeparately() {
    return invoiceSeparately;
  }

  public void setInvoiceSeparately(Boolean invoiceSeparately) {
    this.invoiceSeparately = invoiceSeparately;
  }

  public PUTSubscriptionType invoiceTargetDate(LocalDate invoiceTargetDate) {
    this.invoiceTargetDate = invoiceTargetDate;
    return this;
  }

   /**
   * Date through which to calculate charges if an invoice is generated, as yyyy-mm-dd. Default is current date. 
   * @return invoiceTargetDate
  **/
  @ApiModelProperty(example = "null", value = "Date through which to calculate charges if an invoice is generated, as yyyy-mm-dd. Default is current date. ")
  public LocalDate getInvoiceTargetDate() {
    return invoiceTargetDate;
  }

  public void setInvoiceTargetDate(LocalDate invoiceTargetDate) {
    this.invoiceTargetDate = invoiceTargetDate;
  }

  public PUTSubscriptionType notes(String notes) {
    this.notes = notes;
    return this;
  }

   /**
   * String of up to 500 characters. 
   * @return notes
  **/
  @ApiModelProperty(example = "null", value = "String of up to 500 characters. ")
  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public PUTSubscriptionType preview(Boolean preview) {
    this.preview = preview;
    return this;
  }

   /**
   * If `true` the update is made in preview mode. The default setting is `false`. 
   * @return preview
  **/
  @ApiModelProperty(example = "null", value = "If `true` the update is made in preview mode. The default setting is `false`. ")
  public Boolean getPreview() {
    return preview;
  }

  public void setPreview(Boolean preview) {
    this.preview = preview;
  }

  public PUTSubscriptionType previewType(String previewType) {
    this.previewType = previewType;
    return this;
  }

   /**
   *  The type of preview you will receive. The possible values are `InvoiceItem`, `ChargeMetrics`, or `InvoiceItemChargeMetrics`. The default is `InvoiceItem`. 
   * @return previewType
  **/
  @ApiModelProperty(example = "null", value = " The type of preview you will receive. The possible values are `InvoiceItem`, `ChargeMetrics`, or `InvoiceItemChargeMetrics`. The default is `InvoiceItem`. ")
  public String getPreviewType() {
    return previewType;
  }

  public void setPreviewType(String previewType) {
    this.previewType = previewType;
  }

  public PUTSubscriptionType remove(List<PUTSrpRemoveType> remove) {
    this.remove = remove;
    return this;
  }

  public PUTSubscriptionType addRemoveItem(PUTSrpRemoveType removeItem) {
    this.remove.add(removeItem);
    return this;
  }

   /**
   * Container for removing one or more rate plans. 
   * @return remove
  **/
  @ApiModelProperty(example = "null", value = "Container for removing one or more rate plans. ")
  public List<PUTSrpRemoveType> getRemove() {
    return remove;
  }

  public void setRemove(List<PUTSrpRemoveType> remove) {
    this.remove = remove;
  }

  public PUTSubscriptionType renewalSetting(String renewalSetting) {
    this.renewalSetting = renewalSetting;
    return this;
  }

   /**
   * Specifies whether a termed subscription will remain `TERMED` or change to `EVERGREEN` when it is renewed.   Values are:  * `RENEW_WITH_SPECIFIC_TERM` (default) * `RENEW_TO_EVERGREEN` 
   * @return renewalSetting
  **/
  @ApiModelProperty(example = "null", value = "Specifies whether a termed subscription will remain `TERMED` or change to `EVERGREEN` when it is renewed.   Values are:  * `RENEW_WITH_SPECIFIC_TERM` (default) * `RENEW_TO_EVERGREEN` ")
  public String getRenewalSetting() {
    return renewalSetting;
  }

  public void setRenewalSetting(String renewalSetting) {
    this.renewalSetting = renewalSetting;
  }

  public PUTSubscriptionType renewalTerm(Long renewalTerm) {
    this.renewalTerm = renewalTerm;
    return this;
  }

   /**
   * The length of the period for the subscription renewal term. Default is `0`. 
   * @return renewalTerm
  **/
  @ApiModelProperty(example = "null", value = "The length of the period for the subscription renewal term. Default is `0`. ")
  public Long getRenewalTerm() {
    return renewalTerm;
  }

  public void setRenewalTerm(Long renewalTerm) {
    this.renewalTerm = renewalTerm;
  }

  public PUTSubscriptionType renewalTermPeriodType(String renewalTermPeriodType) {
    this.renewalTermPeriodType = renewalTermPeriodType;
    return this;
  }

   /**
   *  The period type for the subscription renewal term.  This field is used with the `renewalTerm` field to specify the subscription renewal term.  Values are:  * `Month` (default) * `Year` * `Day` * `Week` 
   * @return renewalTermPeriodType
  **/
  @ApiModelProperty(example = "null", value = " The period type for the subscription renewal term.  This field is used with the `renewalTerm` field to specify the subscription renewal term.  Values are:  * `Month` (default) * `Year` * `Day` * `Week` ")
  public String getRenewalTermPeriodType() {
    return renewalTermPeriodType;
  }

  public void setRenewalTermPeriodType(String renewalTermPeriodType) {
    this.renewalTermPeriodType = renewalTermPeriodType;
  }

  public PUTSubscriptionType termStartDate(LocalDate termStartDate) {
    this.termStartDate = termStartDate;
    return this;
  }

   /**
   * Date the subscription term begins, as yyyy-mm-dd. If this is a renewal subscription, this date is different from the subscription start date.  
   * @return termStartDate
  **/
  @ApiModelProperty(example = "null", value = "Date the subscription term begins, as yyyy-mm-dd. If this is a renewal subscription, this date is different from the subscription start date.  ")
  public LocalDate getTermStartDate() {
    return termStartDate;
  }

  public void setTermStartDate(LocalDate termStartDate) {
    this.termStartDate = termStartDate;
  }

  public PUTSubscriptionType termType(String termType) {
    this.termType = termType;
    return this;
  }

   /**
   * Possible values are: `TERMED`, `EVERGREEN`. 
   * @return termType
  **/
  @ApiModelProperty(example = "null", value = "Possible values are: `TERMED`, `EVERGREEN`. ")
  public String getTermType() {
    return termType;
  }

  public void setTermType(String termType) {
    this.termType = termType;
  }

  public PUTSubscriptionType update(List<PUTSrpUpdateType> update) {
    this.update = update;
    return this;
  }

  public PUTSubscriptionType addUpdateItem(PUTSrpUpdateType updateItem) {
    this.update.add(updateItem);
    return this;
  }

   /**
   * Container for updating one or more rate plans. 
   * @return update
  **/
  @ApiModelProperty(example = "null", value = "Container for updating one or more rate plans. ")
  public List<PUTSrpUpdateType> getUpdate() {
    return update;
  }

  public void setUpdate(List<PUTSrpUpdateType> update) {
    this.update = update;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PUTSubscriptionType pUTSubscriptionType = (PUTSubscriptionType) o;
    return Objects.equals(this.cpqBundleJsonIdQT, pUTSubscriptionType.cpqBundleJsonIdQT) &&
        Objects.equals(this.opportunityCloseDateQT, pUTSubscriptionType.opportunityCloseDateQT) &&
        Objects.equals(this.opportunityNameQT, pUTSubscriptionType.opportunityNameQT) &&
        Objects.equals(this.quoteBusinessTypeQT, pUTSubscriptionType.quoteBusinessTypeQT) &&
        Objects.equals(this.quoteNumberQT, pUTSubscriptionType.quoteNumberQT) &&
        Objects.equals(this.quoteTypeQT, pUTSubscriptionType.quoteTypeQT) &&
        Objects.equals(this.add, pUTSubscriptionType.add) &&
        Objects.equals(this.applyCreditBalance, pUTSubscriptionType.applyCreditBalance) &&
        Objects.equals(this.autoRenew, pUTSubscriptionType.autoRenew) &&
        Objects.equals(this.currentTerm, pUTSubscriptionType.currentTerm) &&
        Objects.equals(this.currentTermPeriodType, pUTSubscriptionType.currentTermPeriodType) &&
        Objects.equals(this.customFieldC, pUTSubscriptionType.customFieldC) &&
        Objects.equals(this.includeExistingDraftInvoiceItems, pUTSubscriptionType.includeExistingDraftInvoiceItems) &&
        Objects.equals(this.invoice, pUTSubscriptionType.invoice) &&
        Objects.equals(this.invoiceCollect, pUTSubscriptionType.invoiceCollect) &&
        Objects.equals(this.invoiceSeparately, pUTSubscriptionType.invoiceSeparately) &&
        Objects.equals(this.invoiceTargetDate, pUTSubscriptionType.invoiceTargetDate) &&
        Objects.equals(this.notes, pUTSubscriptionType.notes) &&
        Objects.equals(this.preview, pUTSubscriptionType.preview) &&
        Objects.equals(this.previewType, pUTSubscriptionType.previewType) &&
        Objects.equals(this.remove, pUTSubscriptionType.remove) &&
        Objects.equals(this.renewalSetting, pUTSubscriptionType.renewalSetting) &&
        Objects.equals(this.renewalTerm, pUTSubscriptionType.renewalTerm) &&
        Objects.equals(this.renewalTermPeriodType, pUTSubscriptionType.renewalTermPeriodType) &&
        Objects.equals(this.termStartDate, pUTSubscriptionType.termStartDate) &&
        Objects.equals(this.termType, pUTSubscriptionType.termType) &&
        Objects.equals(this.update, pUTSubscriptionType.update);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cpqBundleJsonIdQT, opportunityCloseDateQT, opportunityNameQT, quoteBusinessTypeQT, quoteNumberQT, quoteTypeQT, add, applyCreditBalance, autoRenew, currentTerm, currentTermPeriodType, customFieldC, includeExistingDraftInvoiceItems, invoice, invoiceCollect, invoiceSeparately, invoiceTargetDate, notes, preview, previewType, remove, renewalSetting, renewalTerm, renewalTermPeriodType, termStartDate, termType, update);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PUTSubscriptionType {\n");
    
    sb.append("    cpqBundleJsonIdQT: ").append(toIndentedString(cpqBundleJsonIdQT)).append("\n");
    sb.append("    opportunityCloseDateQT: ").append(toIndentedString(opportunityCloseDateQT)).append("\n");
    sb.append("    opportunityNameQT: ").append(toIndentedString(opportunityNameQT)).append("\n");
    sb.append("    quoteBusinessTypeQT: ").append(toIndentedString(quoteBusinessTypeQT)).append("\n");
    sb.append("    quoteNumberQT: ").append(toIndentedString(quoteNumberQT)).append("\n");
    sb.append("    quoteTypeQT: ").append(toIndentedString(quoteTypeQT)).append("\n");
    sb.append("    add: ").append(toIndentedString(add)).append("\n");
    sb.append("    applyCreditBalance: ").append(toIndentedString(applyCreditBalance)).append("\n");
    sb.append("    autoRenew: ").append(toIndentedString(autoRenew)).append("\n");
    sb.append("    currentTerm: ").append(toIndentedString(currentTerm)).append("\n");
    sb.append("    currentTermPeriodType: ").append(toIndentedString(currentTermPeriodType)).append("\n");
    sb.append("    customFieldC: ").append(toIndentedString(customFieldC)).append("\n");
    sb.append("    includeExistingDraftInvoiceItems: ").append(toIndentedString(includeExistingDraftInvoiceItems)).append("\n");
    sb.append("    invoice: ").append(toIndentedString(invoice)).append("\n");
    sb.append("    invoiceCollect: ").append(toIndentedString(invoiceCollect)).append("\n");
    sb.append("    invoiceSeparately: ").append(toIndentedString(invoiceSeparately)).append("\n");
    sb.append("    invoiceTargetDate: ").append(toIndentedString(invoiceTargetDate)).append("\n");
    sb.append("    notes: ").append(toIndentedString(notes)).append("\n");
    sb.append("    preview: ").append(toIndentedString(preview)).append("\n");
    sb.append("    previewType: ").append(toIndentedString(previewType)).append("\n");
    sb.append("    remove: ").append(toIndentedString(remove)).append("\n");
    sb.append("    renewalSetting: ").append(toIndentedString(renewalSetting)).append("\n");
    sb.append("    renewalTerm: ").append(toIndentedString(renewalTerm)).append("\n");
    sb.append("    renewalTermPeriodType: ").append(toIndentedString(renewalTermPeriodType)).append("\n");
    sb.append("    termStartDate: ").append(toIndentedString(termStartDate)).append("\n");
    sb.append("    termType: ").append(toIndentedString(termType)).append("\n");
    sb.append("    update: ").append(toIndentedString(update)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

