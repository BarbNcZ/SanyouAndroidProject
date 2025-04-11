import com.androidestudos.fiapchallange.network.APIServer
import com.androidestudos.fiapchallange.network.APIServerDataSource
import com.androidestudos.fiapchallange.network.ErrorInterceptor
import com.androidestudos.fiapchallange.repository.DepartmentsRepository
import com.androidestudos.fiapchallange.repository.EmployeesRepository
import com.androidestudos.fiapchallange.repository.LoginRepository
import com.androidestudos.fiapchallange.repository.RolesRepository
import com.androidestudos.fiapchallange.repository.TasksRepository
import com.androidestudos.fiapchallange.ui.viewmodel.CreateTarefaViewModel
import com.androidestudos.fiapchallange.ui.viewmodel.DeleteTarefaViewModel
import com.androidestudos.fiapchallange.ui.viewmodel.EmployeesViewModel
import com.androidestudos.fiapchallange.ui.viewmodel.LoginViewModel
import com.androidestudos.fiapchallange.ui.viewmodel.MenuViewModel
import com.androidestudos.fiapchallange.ui.viewmodel.TarefaViewModel
import com.androidestudos.fiapchallange.ui.viewmodel.TarefasViewModel
import okhttp3.OkHttpClient
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val tarefasModulo = module{

    viewModel {
        TarefasViewModel(get())
    }

    viewModel {
        TarefaViewModel(get())
    }

    viewModel {
        LoginViewModel(get())
    }

    viewModel {
        EmployeesViewModel(get(), get(), get())
    }

    viewModel {
        CreateTarefaViewModel(get(), get())
    }

    viewModel {
        DeleteTarefaViewModel(get(), get())
    }

    viewModel {
        MenuViewModel()
    }

    single {
        TasksRepository(get())
    }

    single {
        RolesRepository(get())
    }

    single {
        DepartmentsRepository(get())
    }

    single {
        LoginRepository(get())
    }

    single {
        EmployeesRepository(get())
    }

    single {
        APIServerDataSource(get())
    }

    single {
        buildAPI()
    }

}

private fun buildAPI(): APIServer {
    val client = OkHttpClient.Builder()
        .addInterceptor(ErrorInterceptor())
        .build()
    return Retrofit.Builder()
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("http://10.0.2.2:8000")
        //.baseUrl("https://9baf-2a09-bac1-5e00-00-6b-a6.ngrok-free.app")
        .build()
        .create(APIServer::class.java)
}

/* di = dependency injection
* essa pasta armazena os arquivos onde estao os modulos do koin que e a biblioteca
* de injecao de dependencia
* injecao de dependencia = uma estrutura de gestao de objetos instanciados em memoria (ram)
* da qual trabalha com objetos do tipo singleton, ou seja, objetos que sao instanciados apenas
* uma unica vez economizando assim espaco em memoria e processamento.
* Alem de tambem armazenar objetos do tipo view model que sao as classes que trabalham em conjunto com a view
* (tela com conteundo na qual o usuario ve e interage)
* Esses modulos tambem podem trabalhar com factory no qual e o contrario de singleton, ou seja,
* os objetos sao instanciados novamente toda vez em que for injetados e chamados por outras classes
* das quais dependem dele */