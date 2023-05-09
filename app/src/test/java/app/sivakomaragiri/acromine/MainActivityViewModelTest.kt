package app.sivakomaragiri.acromine
import app.sivakomaragiri.acromine.common.LiveDataExt.getOrAwaitValue
import app.sivakomaragiri.acromine.domain.AcromineRepository
import app.sivakomaragiri.acromine.data.LongFormResponse
import app.sivakomaragiri.acromine.data.LongForms
import app.sivakomaragiri.acromine.netwok.NetworkResult
import app.sivakomaragiri.acromine.ui.MainActivityViewModel
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


class MainActivityViewModelTest {

    @Mock
    private lateinit var mockRepository: AcromineRepository
    private lateinit var viewModel: MainActivityViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this);
        viewModel = MainActivityViewModel(mockRepository)
    }
    @Test
    fun `when getLongForms() returns success respose`() = runBlocking {
        val longForms = LongFormResponse(
            "hmm",
            listOf(
                LongForms("test long form1", 10, 2000, vars = mockk()),
                LongForms("test long form2", 100, 2010)
            )
        )
        val result = NetworkResult.Success(longForms) as NetworkResult<LongFormResponse>
        `when`(mockRepository.getLongForms("hmm") ).thenReturn(result)
        viewModel.getLongForm("hmm")

        assertEquals(result, viewModel.longFormResponse.getOrAwaitValue())

    }
}