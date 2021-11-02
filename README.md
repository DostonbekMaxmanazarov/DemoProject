# DemoProject
Dagger Hilt, MVP Moxy, Retrofit, Kotlin coroutine, Sealed class

//Presenterda ma'lumotlarni handler qilish

        override fun loadPost() {
        viewState.showRefresh()
        presenterScope.launch {
            try {
                repository.loadPost().onData {
                    viewState.addPosts(it)
                }.onResource {
                    viewState.showMessage(it)
                }.onMessage {
                    viewState.showMessage(it)
                }.onFailure {
                }
            } catch (e: Exception) {
                e.printStackTrace()
                viewState.onFail()
            } finally {
                viewState.hideRefresh()
            }
        }
    }
   
//Api module(Dagger Hilt)
    
    @Module
    @InstallIn(SingletonComponent::class)
    class ApiModule {

    @Provides
    @Singleton
    fun getPostApi(retrofit: Retrofit): PostApi = retrofit.create(PostApi::class.java)
    }
    
//Retrofit module(Dagger Hilt)
    
    @Module
    @InstallIn(SingletonComponent::class)
    class RetrofitModule {
    @Provides
    @Singleton
    fun getRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
     }

//Repository

    class PostRepository @Inject constructor(private val api: PostApi) : IPostContract.Model {
    override suspend fun loadPost(): ResultData<List<Post>> {
        val response = api.loadPost()

        when (response.code()) {
            200 -> {
                return ResultData.data(response.body()!!)
            }
            404 -> {
                return ResultData.resource(R.string.not_found)
            }
            in 500..600 -> {
                return ResultData.resource(R.string.internal_server_error)
            }
            else -> return if (response.body() == null) {
                ResultData.resource(R.string.server_error)
            } else {
                ResultData.message(response.body().toString())
            }
        }
    }
    }
