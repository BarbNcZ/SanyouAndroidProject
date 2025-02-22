import com.androidestudos.fiapchallange.network.APIServerDataSource
import com.androidestudos.fiapchallange.repository.TarefasRepository
import com.androidestudos.fiapchallange.ui.viewmodel.TarefasViewModel
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