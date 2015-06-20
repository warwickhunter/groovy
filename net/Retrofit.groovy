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

RestAdapter restAdapter1 = new RestAdapter.Builder()
    .setEndpoint("https://api.github.com")
    .build();

class Todo {
    int userId
    int id
    String title
    boolean completed
}

interface TodoService {
   @GET("/todos")
   List<Todo> getTodo()
}

RestAdapter restAdapter2 = new RestAdapter.Builder()
    .setEndpoint("http://jsonplaceholder.typicode.com")
    .build();

GitHubService service1 = restAdapter1.create(GitHubService.class)

TodoService service2 = restAdapter2.create(TodoService.class)

println "Github repos for warwickhunter"
service1.listRepos("warwickhunter").each { repo ->
    println "$repo.name $repo.clone_url"
}

println ""
println "Jsonplaceholder TODOs"
service2.getTodo().each { todo ->
    println "id: $todo.id userId: $todo.userId complete: $todo.completed $todo.title"
}

return 0