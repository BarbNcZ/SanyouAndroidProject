import com.androidestudos.fiapchallange.APIServerDataSource
import com.androidestudos.fiapchallange.TarefasRepository
import com.androidestudos.fiapchallange.TarefasViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val tarefasModulo = module{

    viewModel {
        TarefasViewModel(get())
    }

    single {
        TarefasRepository(get())
    }

    single {
        APIServerDataSource()
    }

}