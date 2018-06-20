package prj.mob1.prjmob1.show

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import prj.mob1.prjmob1.retrofitUtil.models.CreditResponse
import prj.mob1.prjmob1.retrofitUtil.models.ReviewsResponse
import prj.mob1.prjmob1.retrofitUtil.models.SimilarMoviesResponse
import prj.mob1.prjmob1.season.Season

/**
 * Created by sol on 26/03/2018.
 */
data class TVShow(@SerializedName("id") val id:Int,
                  @SerializedName("name") val title:String,
                  @SerializedName("number_of_episodes") val nbr_episodes: Int,
                  val tags: String,
                  @SerializedName("episode_run_time") val duration: IntArray,
                  @SerializedName("poster_path") val posterId: String,
                  @SerializedName("overview") var overview: String,
                  @SerializedName("vote_count") var voteCount: Int,
                  @SerializedName("vote_average") var rating: Double,
                  @SerializedName("seasons") var seasons: List<Season>,
                  @SerializedName("credits") var credits: CreditResponse,
                  @SerializedName("similar") var similar: SimilarMoviesResponse,
                  @SerializedName("reviews") var reviews: ReviewsResponse
                    ) : Parcelable {


    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.createIntArray(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readDouble(),
            parcel.createTypedArrayList(Season),
            parcel.readParcelable(CreditResponse::class.java.classLoader),
            parcel.readParcelable(SimilarMoviesResponse::class.java.classLoader),
            parcel.readParcelable(ReviewsResponse::class.java.classLoader)) {
    }

    constructor() : this(0,"",0,"", intArrayOf(),"","",0,0.0, listOf<Season>(),CreditResponse(0, listOf(), listOf()),
            SimilarMoviesResponse(0, listOf()),ReviewsResponse(0, listOf()))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeInt(nbr_episodes)
        parcel.writeString(tags)
        parcel.writeIntArray(duration)
        parcel.writeString(posterId)
        parcel.writeString(overview)
        parcel.writeInt(voteCount)
        parcel.writeDouble(rating)
        parcel.writeTypedList(seasons)
        parcel.writeParcelable(credits, flags)
        parcel.writeParcelable(similar, flags)
        parcel.writeParcelable(reviews, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TVShow> {
        override fun createFromParcel(parcel: Parcel): TVShow {
            return TVShow(parcel)
        }

        override fun newArray(size: Int): Array<TVShow?> {
            return arrayOfNulls(size)
        }
    }


}