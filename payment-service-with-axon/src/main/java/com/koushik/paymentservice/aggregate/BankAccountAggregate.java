package com.koushik.paymentservice.aggregate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.koushik.paymentservice.command.CreateAccountCommand;
import com.koushik.paymentservice.command.CreditMoneyCommand;
import com.koushik.paymentservice.command.DebitMoneyCommand;
import com.koushik.paymentservice.event.AccountCreatedEvent;
import com.koushik.paymentservice.event.MoneyCreditedEvent;
import com.koushik.paymentservice.event.MoneyDebitedEvent;
import com.koushik.paymentservice.exception.InsufficientBalanceException;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Aggregate
public class BankAccountAggregate {

    @AggregateIdentifier
    private UUID id;
    private BigDecimal balance;
    private String owner;

    @CommandHandler
    public BankAccountAggregate(CreateAccountCommand command) {

        AggregateLifecycle.apply(
                new AccountCreatedEvent(
                        command.getAccountId(),
                        command.getInitialBalance(),
                        command.getOwner()
                )
        );
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event) {
        this.id = event.getId();
        this.owner = event.getOwner();
        this.balance = event.getInitialBalance();
    }

    @CommandHandler
    public void handle(CreditMoneyCommand command) {
        AggregateLifecycle.apply(
                new MoneyCreditedEvent(
                        command.getAccountId(),
                        command.getCreditAmount()
                )
        );
    }

    @EventSourcingHandler
    public void on(MoneyCreditedEvent event) {
        this.balance = this.balance.add(event.getCreditAmount());
    }

    @CommandHandler
    public void handle(DebitMoneyCommand command) {
        AggregateLifecycle.apply(
                new MoneyDebitedEvent(
                        command.getAccountId(),
                        command.getDebitAmount()
                )
        );
    }

    @EventSourcingHandler
    public void on(MoneyDebitedEvent event) throws InsufficientBalanceException {
        if (this.balance.compareTo(event.getDebitAmount()) < 0) {
            throw new InsufficientBalanceException(event.getId(), event.getDebitAmount());
        }
        this.balance = this.balance.subtract(event.getDebitAmount());
    }
}
