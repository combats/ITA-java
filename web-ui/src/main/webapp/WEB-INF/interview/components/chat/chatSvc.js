angular.module('interview').factory('Questions',function() {
    return {
        get: function(){
            return [{
            question: "When did WWII start?",
            comment: "lol, this guy is so stupid",
            mark: 8,
            weight: 4,
            active: true
    },
        {
            question: "When did WWI start?",
            comment: "lol, this guy is not stupid",
            mark: 7,
            weight: 3,
            answered: true
    },
        {
            question: "When did we start?",
            comment: "lol, this guy is very stupid",
            mark: 6,
            weight: 1
        }
                   ];
        }
    }
})
