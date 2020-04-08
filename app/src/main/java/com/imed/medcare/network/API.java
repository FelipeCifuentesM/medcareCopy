package com.imed.medcare.network;

import com.imed.medcare.network.request.AttachmentRequest;
import com.imed.medcare.network.request.FirebaseTokenRequest;
import com.imed.medcare.network.request.LoginRequest;
import com.imed.medcare.network.request.MasiveAnswerTreatmentPollRequest;
import com.imed.medcare.network.request.MasiveAnswerUserPollRequest;
import com.imed.medcare.network.request.MasiveAttachmentRequest;
import com.imed.medcare.network.request.PersonalProfileRequest;
import com.imed.medcare.network.response.AnswerTreatmentPollResponse;
import com.imed.medcare.network.response.AnswerUserPollResponse;
import com.imed.medcare.network.response.AttachmentResponse;
import com.imed.medcare.network.response.DocumentSaveResponse;
import com.imed.medcare.network.response.FirebaseTokenResponse;
import com.imed.medcare.network.response.HistoryPrescriptionResponse;
import com.imed.medcare.network.response.HistoryResponse;
import com.imed.medcare.network.response.InvitationResponse;
import com.imed.medcare.network.response.LoginResponse;
import com.imed.medcare.network.response.MasiveAttachmentResponse;
import com.imed.medcare.network.response.PersonalProfileResponse;
import com.imed.medcare.network.response.PersonalProfileSetAnswerResponse;
import com.imed.medcare.network.response.ProfileResponse;
import com.imed.medcare.network.response.MedicPollResponse;
import com.imed.medcare.network.response.RespondedPollResponse;
import com.imed.medcare.network.response.TreatmentPollResponse;
import com.imed.medcare.network.response.TreatmentResponse;
import com.imed.medcare.network.response.UserPollResponse;

import java.util.List;

import okhttp3.MultipartBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {

    //Login Request
    @POST("api/login")
    Call<LoginResponse> postLogin(@Header("api-key") String header,
                                  @Body LoginRequest loginRequest);

    @GET("api/profile")
    Call<ProfileResponse> getProfile(@Header("api-key") String header,
                                      @Header("access-token") String accessToken);

    @GET("api/treatments")
    Call<TreatmentResponse> getTreatment(@Header("api-key") String header,
                                         @Header("access-token") String accessToken);

    @GET("api/treatments/{id}/docs/{path}")
    Call<String> getDocument(@Path("id") int id,
                                   @Path("path") String path,
                                   @Header("api-key") String header,
                                   @Header("access-token") String accessToken);

    @Multipart
    @POST("api/treatments/{id_treatment}/docs")
    Call<DocumentSaveResponse> saveDocumentResponse(@Path("id_treatment") int idTreatment,
                                                    @Part List<MultipartBody.Part> file,
                                                    @Header("api-key") String apiKey,
                                                    @Header("access-token") String accessToken);

    @GET("api/treatments/{id}/polls/current")
    Call<TreatmentPollResponse> getTreatmentPollsResponse(@Path("id") int id,
                                                          @Header("api-key") String header,
                                                          @Header("access-token") String accessToken);


    @GET("api/users/polls/current")
    Call<UserPollResponse> getUserPollsResponse(@Header("api-key") String header,
                                                @Header("access-token") String accessToken);

    @POST("api/treatments/{id_poll}/poll_period/{period}/answers")
    Call<AnswerTreatmentPollResponse> postAnswerTreatmentPollResponse(@Path("id_poll") int idPoll,
                                                                      @Path("period") int period,
                                                                      @Header("api-key") String header,
                                                                      @Header("access-token") String accessToken,
                                                                      @Body MasiveAnswerTreatmentPollRequest masiveAnswerTreatmentPollRequest);


    @POST("api/users/poll_period/{period}/answers")
    Call<AnswerUserPollResponse> postAnswerUserPollResponse(@Path("period") int period,
                                                            @Header("api-key") String header,
                                                            @Header("access-token") String accessToken,
                                                            @Body MasiveAnswerUserPollRequest masiveAnswerUserPollRequest);

    @GET("api/treatments/{id_treatment}/questionary")
    Call<MedicPollResponse> getQuestionaryResponse(@Path("id_treatment") int idTreatment,
                                                   @Header("api-key") String header,
                                                   @Header("access-token") String accessToken);

    @GET("api/treatments/{id_treatment}/polls/")
    Call<HistoryResponse> getHistoryTreatmentListResponse(@Path("id_treatment") int idTreatment,
                                                          @Header("api-key") String header,
                                                          @Header("access-token") String accessToken);

    @GET("api/users/polls")
    Call<HistoryResponse> getHistoryListResponse(@Header("api-key") String header,
                                                     @Header("access-token") String accessToken);

    @GET("api/responded_polls/{id_poll}")
    Call<RespondedPollResponse> getHistoryResponse(@Path("id_poll") int idInvitation,
                                                   @Header("api-key") String header,
                                                   @Header("access-token") String accessToken);

    @POST("api/invitations/{id_invitation}/accept")
    Call<InvitationResponse> postInvitation(@Path("id_invitation") int idInvitation,
                                            @Header("api-key") String header,
                                            @Header("access-token") String accessToken);

    @GET("api/v2/personal-record")
    Call<PersonalProfileResponse> getProfilePersonal(@Header("api-key") String header,
                                                     @Query("access_token") String accessToken);

    @GET("api/v2/lifestyle-record")
    Call<PersonalProfileResponse> getPersonalHabits(@Header("api-key") String header,
                                                 @Query("access_token") String accessToken);

    @GET("api/v2/medical-record")
    Call<PersonalProfileResponse> getPersonalMedics(@Header("api-key") String header,
                                                  @Query("access_token") String accessToken);

    @POST("api/prescriptions/{id_prescription}/attachment")
    Call<AttachmentResponse> postAttachment(@Path("id_prescription") int idPrescription,
                                            @Header("api-key") String header,
                                            @Header("access-token") String accessToken,
                                            @Body AttachmentRequest attachmentRequest);


    @GET("api/treatments/{id_treatment}/prescriptions/history")
    Call<HistoryPrescriptionResponse> getHistoryPrescription(@Header("api-key") String apiKey,
                                                             @Header("access-token") String accessToken,
                                                             @Path("id_treatment") int treatment,
                                                             @Query("start") String dateStart,
                                                             @Query("end") String dateEnd);

    @POST("api/prescriptions/attachment/multiple")
    Call<MasiveAttachmentResponse> postMassiveAttachment(@Header("api-key") String header,
                                                         @Header("access-token") String accessToken,
                                                         @Body MasiveAttachmentRequest masiveAttachmentRequest);

    @POST("api/v2/personal-record")
    Call<PersonalProfileSetAnswerResponse> postPersonalProfileSetAnswerResponse(@Header("api-key") String header,
                                                                                @Query("access_token") String accessToken,
                                                                                @Body PersonalProfileRequest personalProfileRequest);

    @POST("api/v2/lifestyle-record")
    Call<PersonalProfileSetAnswerResponse> postPersonalHabitsSetAnswerResponse(@Header("api-key") String header,
                                                                                @Query("access_token") String accessToken,
                                                                                @Body PersonalProfileRequest personalProfileRequest);

    @POST("api/v2/medical-record")
    Call<PersonalProfileSetAnswerResponse> postPersonalMedicSetAnswerResponse(@Header("api-key") String header,
                                                                                @Query("access_token") String accessToken,
                                                                                @Body PersonalProfileRequest personalProfileRequest);
    @POST("api/users/device")
    Call<FirebaseTokenResponse> postCreateFirebaseTokenResponse(@Header("api-key") String header,
                                                          @Header("access-token") String accessToken,
                                                          @Body FirebaseTokenRequest firebaseTokenRequest);

    @POST("api/users/device/delete")
    Call<FirebaseTokenResponse> postDeleteFirebaseTokenResponse(@Header("api-key") String header,
                                                          @Header("access-token") String accessToken,
                                                          @Body FirebaseTokenRequest firebaseTokenRequest);
}
