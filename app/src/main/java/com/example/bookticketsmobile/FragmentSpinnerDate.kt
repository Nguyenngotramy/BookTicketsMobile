import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.bookticketsmobile.databinding.ActivityRegisterBinding
import com.example.bookticketsmobile.databinding.FragmentSpinnerDateBinding
import java.util.Calendar

class FragmentSpinnerDate : Fragment() {

    private final lateinit var binding: ActivityRegisterBinding

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =ActivityRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Tạo danh sách ngày
        val days = (1..31).map { it.toString() }

        // Tạo danh sách tháng
        val months = (1..12).map { it.toString() }

        // Tạo danh sách năm (ví dụ: từ năm 1900 đến năm hiện tại)
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val years = (1900..currentYear).map { it.toString() }

        // Tạo Adapter cho ngày
        val dayAdapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, days)
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Tạo Adapter cho tháng
        val monthAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, months)
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Tạo Adapter cho năm
        val yearAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, years)
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Lấy ra Spinner từ ViewBinding
        val daySpinner = binding.spinner
        val monthSpinner = binding.spinner
        val yearSpinner = binding.spinner

        // Set Adapter cho Spinner
        daySpinner.adapter = dayAdapter
        monthSpinner.adapter = monthAdapter
        yearSpinner.adapter = yearAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentSpinnerDate().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
