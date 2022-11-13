package lotto.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.domain.Lotto;
import lotto.repository.LottoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoServiceTest {
    private final LottoService lottoService = new LottoService();

    @DisplayName("(입력된 금액 / 1,000)의 개수 만큼의 로또 구매 성공 테스트")
    @ParameterizedTest
    @CsvSource({"1000,1", "2000,2", "3000,3", "1230000,1230"})
    void 입력금액_로또_구매(int inputPrice, int countOfLotto) {
        // given
        // when
        lottoService.buyLottos(inputPrice);
        List<Lotto> lottos = LottoRepository.getInstance()
                .findAll();

        // then
        assertThat(lottos.size()).isEqualTo(countOfLotto);
    }

    @DisplayName("구매한 로또의 결과 리스트를 만드는 기능 테스트")
    @Test
    void 구매한_로또_결과_리스트_검증() {
        // given
        LottoRepository.getInstance()
                .generateLottos(1);
        List<Lotto> lottos = LottoRepository.getInstance()
                .findAll();

        // when
        List<String> boughtResultMessage = lottoService.createBoughtResultMessage();
        System.out.println(boughtResultMessage.get(1));
        // then
        assertThat(lottos.get(0).toString()).isEqualTo(boughtResultMessage.get(1));
    }
}