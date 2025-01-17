package ru.focus.start.loan.features.loan.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.CreationTimestamp
import ru.focus.start.loan.features.auth.model.UserEntity
import java.math.BigDecimal
import java.util.Date
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Temporal
import javax.persistence.TemporalType

@Entity(name = "loans")
data class Loan(
	val firstName: String,

	val lastName: String,

	@Column(name = "phone_number")
	val phoneNumber: String,

	val amount: BigDecimal,

	var percent: Double,

	var period: Int,

	@CreationTimestamp
	val date: Date,

	@Enumerated(EnumType.STRING)
	val state: LoanState,

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	val userEntity: UserEntity,

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	val id: Long = 0,
) {

	override fun toString(): String {
		return "Loan($id, $firstName, $lastName, $phoneNumber, $amount, $percent, $period, $state)"
	}
}