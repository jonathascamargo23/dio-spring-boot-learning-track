package dio.budgeting.application;

import dio.budgeting.domain.Category;
import dio.budgeting.domain.TransactionRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

@Service
public class GetTotalByCategoryUseCase {

    private final TransactionRepository transactionRepository;

    public GetTotalByCategoryUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Tool(
        name = "get-total-by-category",
        description = "Calcula o valor total das transações de uma categoria"
    )
    public long execute(
            @ToolParam(description = "Categoria das transações") Category category) {

        return transactionRepository.findAllByCategory(category)
                .stream()
                .mapToLong(transaction -> transaction.getAmount())
                .sum();
    }
}
