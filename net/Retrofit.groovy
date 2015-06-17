/**
 * Experiment with the Retrofit JSON REST library
 * 
 * http://square.github.io/retrofit/
 *
 * @author Warwick Hunter
 * @since  2015-06-18
 */

// Use Grape to handle the downloading of required jars, nice!
@Grab(group='com.squareup.retrofit', module='retrofit', version='1.9.0')

import retrofit.*
import retrofit.http.*

interface GitHubService {
  @GET("/users/{user}/repos")
  List<Object> listRepos(@Path("user") String user);
}

RestAdapter restAdapter = new RestAdapter.Builder()
    .setEndpoint("https://api.github.com")
    .build();

GitHubService service = restAdapter.create(GitHubService.class);

println service.listRepos("octocat");

return 0