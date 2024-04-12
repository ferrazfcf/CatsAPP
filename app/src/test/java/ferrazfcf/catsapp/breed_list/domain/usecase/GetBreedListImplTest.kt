package ferrazfcf.catsapp.breed_list.domain.usecase

import ferrazfcf.catsapp.breed_list.data.dto.BreedItemDTO
import ferrazfcf.catsapp.breed_list.domain.model.BreedItem
import ferrazfcf.catsapp.breed_list.domain.repository.BreedItemRepository
import ferrazfcf.catsapp.core.data.dto.ImageDTO
import ferrazfcf.catsapp.core.domain.util.Resource
import ferrazfcf.catsapp.core.error.CatsAppError
import ferrazfcf.catsapp.core.error.CatsAppException
import ferrazfcf.catsapp.core.logger.LoggerService
import ferrazfcf.catsapp.rule.CoroutineRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.just
import io.mockk.runs
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetBreedListImplTest {
    @get:Rule
    val mockkRule = MockKRule(this)

    @get:Rule
    val coroutineRule = CoroutineRule()

    @MockK
    lateinit var breedItemRepository: BreedItemRepository

    @MockK
    lateinit var loggerService: LoggerService

    @InjectMockKs
    lateinit var getBreedListImpl: GetBreedListImpl

    @Before
    fun setUp() {
        coEvery { loggerService.sendException(any()) } just runs
    }

    @Test
    fun `when successfully get breed list then filter malformed data`() {
        coEvery { breedItemRepository.getBreedItemList(any()) } answers { breedItemDTOList }

        coroutineRule.runTest {
            val result = getBreedListImpl()
            assertEquals(Resource.Success(breedItemList), result)

            coVerify(exactly = 1) { breedItemRepository.getBreedItemList(any()) }
            coVerify(exactly = 3) { loggerService.sendException(any()) }
        }
    }

    @Test
    fun `when repository throws an error then catch`() {
        val exception = CatsAppException(CatsAppError.UNKNOWN_ERROR)
        coEvery { breedItemRepository.getBreedItemList(any()) } throws exception

        coroutineRule.runTest {
            val result = getBreedListImpl()
            assertEquals(
                Resource.Error(exception),
                result
            )

            coVerify(exactly = 1) { breedItemRepository.getBreedItemList(any()) }
            coVerify(exactly = 0) { loggerService.sendException(any()) }
        }
    }

    companion object {
        private val breedItemDTO = BreedItemDTO(
            id = "abys",
            name = "Abyssinian",
            origin = "Egypt",
            image = ImageDTO("https://cdn2.thecatapi.com/images/ozEvzdVM-.jpg")
        )
        private val breedItem = BreedItem(
            id = "abys",
            name = "Abyssinian",
            origin = "Egypt",
            image = "https://cdn2.thecatapi.com/images/ozEvzdVM-.jpg"
        )
        private val breedItemDTOList = listOf(
            breedItemDTO,
            breedItemDTO.copy(image = null),
            breedItemDTO.copy(id = null),
            breedItemDTO.copy(name = null),
            breedItemDTO.copy(origin = null),
            breedItemDTO,
            breedItemDTO
        )
        private val breedItemList = listOf(
            breedItem,
            breedItem.copy(image = null),
            breedItem,
            breedItem
        )
    }
}
