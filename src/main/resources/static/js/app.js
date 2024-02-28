function handleEvent(eventType, selector, handler) {
  document.addEventListener(eventType, function(event) {
    if (event.target.matches(selector + ', ' + selector + ' *')) {
      handler.apply(event.target.closest(selector), arguments);
    }
  });
}

handleEvent('submit', '.js-submit-confirm', function(event) {
  if (!confirm(this.getAttribute('data-confirm-message'))) {
    event.preventDefault();
    return false;
  }
  return true;
});

document.addEventListener('DOMContentLoaded', function() {
    var filterForm = document.getElementById('filterForm');
    var jobTypeFilters = document.querySelectorAll('.jobTypeFilter');
    var workLocationFilters = document.querySelectorAll('.workLocationFilter');

    function applyFilters() {
        filterForm.submit();
    }

    jobTypeFilters.forEach(function(filter) {
        filter.addEventListener('change', applyFilters);
    });

    workLocationFilters.forEach(function(filter) {
        filter.addEventListener('change', applyFilters);
    });
});

document.addEventListener("DOMContentLoaded", function() {
    let requirementCounter = document.querySelectorAll('#requirements .requirement').length;

    document.getElementById("add-requirement").addEventListener("click", function() {
        const requirementsDiv = document.getElementById("requirements");
        const newRequirement = document.createElement("div");
        newRequirement.className = "requirement";
        newRequirement.innerHTML = `
            <div class="row mb-3">
                <div class="col-md-5">
                    <input type="text" class="form-control" name="requirements[${requirementCounter}].label" placeholder="Label">
                </div>
                <div class="col-md-5">
                    <input type="number" class="form-control" name="requirements[${requirementCounter}].minimumYears" placeholder="Minimum Years">
                </div>
                <div class="col-md-2">
                    <button type="button" class="btn btn-danger remove-requirement">Remove</button>
                </div>
            </div>
        `;
        requirementsDiv.appendChild(newRequirement);
        requirementCounter++;
    });

    document.addEventListener("click", function(e) {
        if (e.target && e.target.classList.contains("remove-requirement")) {
            e.target.closest(".requirement").remove();
            requirementCounter--;
        }
    });

    document.getElementById("postForm").addEventListener("submit", function(event) {
        if (!validateForm()) {
        }
    });

    function validateForm() {
        const requirementLabels = document.querySelectorAll('input[name^="requirement_label"]');
        const requirementYears = document.querySelectorAll('input[name^="requirement_year"]');
        for (let i = 0; i < requirementLabels.length; i++) {
            const label = requirementLabels[i].value.trim();
            const year = requirementYears[i].value.trim();
            if (label === '' || year === '') {
                alert('All requirement fields must be filled.');
                return false;
            }
        }
        return true;
    }
});