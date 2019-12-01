package top.aftery.community.model;

import java.util.ArrayList;
import java.util.List;

public class QuestionuserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public QuestionuserExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getOffset() {
        return offset;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNull() {
            addCriterion("gmt_create is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNotNull() {
            addCriterion("gmt_create is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateEqualTo(Long value) {
            addCriterion("gmt_create =", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotEqualTo(Long value) {
            addCriterion("gmt_create <>", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThan(Long value) {
            addCriterion("gmt_create >", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThanOrEqualTo(Long value) {
            addCriterion("gmt_create >=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThan(Long value) {
            addCriterion("gmt_create <", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThanOrEqualTo(Long value) {
            addCriterion("gmt_create <=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIn(List<Long> values) {
            addCriterion("gmt_create in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotIn(List<Long> values) {
            addCriterion("gmt_create not in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateBetween(Long value1, Long value2) {
            addCriterion("gmt_create between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotBetween(Long value1, Long value2) {
            addCriterion("gmt_create not between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNull() {
            addCriterion("gmt_modified is null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNotNull() {
            addCriterion("gmt_modified is not null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedEqualTo(Long value) {
            addCriterion("gmt_modified =", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotEqualTo(Long value) {
            addCriterion("gmt_modified <>", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThan(Long value) {
            addCriterion("gmt_modified >", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThanOrEqualTo(Long value) {
            addCriterion("gmt_modified >=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThan(Long value) {
            addCriterion("gmt_modified <", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThanOrEqualTo(Long value) {
            addCriterion("gmt_modified <=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIn(List<Long> values) {
            addCriterion("gmt_modified in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotIn(List<Long> values) {
            addCriterion("gmt_modified not in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedBetween(Long value1, Long value2) {
            addCriterion("gmt_modified between", value1, value2, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotBetween(Long value1, Long value2) {
            addCriterion("gmt_modified not between", value1, value2, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(Integer value) {
            addCriterion("creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(Integer value) {
            addCriterion("creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(Integer value) {
            addCriterion("creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(Integer value) {
            addCriterion("creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(Integer value) {
            addCriterion("creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(Integer value) {
            addCriterion("creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<Integer> values) {
            addCriterion("creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<Integer> values) {
            addCriterion("creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(Integer value1, Integer value2) {
            addCriterion("creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(Integer value1, Integer value2) {
            addCriterion("creator not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCommentCountIsNull() {
            addCriterion("comment_count is null");
            return (Criteria) this;
        }

        public Criteria andCommentCountIsNotNull() {
            addCriterion("comment_count is not null");
            return (Criteria) this;
        }

        public Criteria andCommentCountEqualTo(Integer value) {
            addCriterion("comment_count =", value, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountNotEqualTo(Integer value) {
            addCriterion("comment_count <>", value, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountGreaterThan(Integer value) {
            addCriterion("comment_count >", value, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("comment_count >=", value, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountLessThan(Integer value) {
            addCriterion("comment_count <", value, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountLessThanOrEqualTo(Integer value) {
            addCriterion("comment_count <=", value, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountIn(List<Integer> values) {
            addCriterion("comment_count in", values, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountNotIn(List<Integer> values) {
            addCriterion("comment_count not in", values, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountBetween(Integer value1, Integer value2) {
            addCriterion("comment_count between", value1, value2, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountNotBetween(Integer value1, Integer value2) {
            addCriterion("comment_count not between", value1, value2, "commentCount");
            return (Criteria) this;
        }

        public Criteria andViewCountIsNull() {
            addCriterion("view_count is null");
            return (Criteria) this;
        }

        public Criteria andViewCountIsNotNull() {
            addCriterion("view_count is not null");
            return (Criteria) this;
        }

        public Criteria andViewCountEqualTo(Integer value) {
            addCriterion("view_count =", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountNotEqualTo(Integer value) {
            addCriterion("view_count <>", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountGreaterThan(Integer value) {
            addCriterion("view_count >", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("view_count >=", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountLessThan(Integer value) {
            addCriterion("view_count <", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountLessThanOrEqualTo(Integer value) {
            addCriterion("view_count <=", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountIn(List<Integer> values) {
            addCriterion("view_count in", values, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountNotIn(List<Integer> values) {
            addCriterion("view_count not in", values, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountBetween(Integer value1, Integer value2) {
            addCriterion("view_count between", value1, value2, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountNotBetween(Integer value1, Integer value2) {
            addCriterion("view_count not between", value1, value2, "viewCount");
            return (Criteria) this;
        }

        public Criteria andLikeCountIsNull() {
            addCriterion("like_count is null");
            return (Criteria) this;
        }

        public Criteria andLikeCountIsNotNull() {
            addCriterion("like_count is not null");
            return (Criteria) this;
        }

        public Criteria andLikeCountEqualTo(Integer value) {
            addCriterion("like_count =", value, "likeCount");
            return (Criteria) this;
        }

        public Criteria andLikeCountNotEqualTo(Integer value) {
            addCriterion("like_count <>", value, "likeCount");
            return (Criteria) this;
        }

        public Criteria andLikeCountGreaterThan(Integer value) {
            addCriterion("like_count >", value, "likeCount");
            return (Criteria) this;
        }

        public Criteria andLikeCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("like_count >=", value, "likeCount");
            return (Criteria) this;
        }

        public Criteria andLikeCountLessThan(Integer value) {
            addCriterion("like_count <", value, "likeCount");
            return (Criteria) this;
        }

        public Criteria andLikeCountLessThanOrEqualTo(Integer value) {
            addCriterion("like_count <=", value, "likeCount");
            return (Criteria) this;
        }

        public Criteria andLikeCountIn(List<Integer> values) {
            addCriterion("like_count in", values, "likeCount");
            return (Criteria) this;
        }

        public Criteria andLikeCountNotIn(List<Integer> values) {
            addCriterion("like_count not in", values, "likeCount");
            return (Criteria) this;
        }

        public Criteria andLikeCountBetween(Integer value1, Integer value2) {
            addCriterion("like_count between", value1, value2, "likeCount");
            return (Criteria) this;
        }

        public Criteria andLikeCountNotBetween(Integer value1, Integer value2) {
            addCriterion("like_count not between", value1, value2, "likeCount");
            return (Criteria) this;
        }

        public Criteria andTagIsNull() {
            addCriterion("tag is null");
            return (Criteria) this;
        }

        public Criteria andTagIsNotNull() {
            addCriterion("tag is not null");
            return (Criteria) this;
        }

        public Criteria andTagEqualTo(String value) {
            addCriterion("tag =", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotEqualTo(String value) {
            addCriterion("tag <>", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThan(String value) {
            addCriterion("tag >", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThanOrEqualTo(String value) {
            addCriterion("tag >=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThan(String value) {
            addCriterion("tag <", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThanOrEqualTo(String value) {
            addCriterion("tag <=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLike(String value) {
            addCriterion("tag like", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotLike(String value) {
            addCriterion("tag not like", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagIn(List<String> values) {
            addCriterion("tag in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotIn(List<String> values) {
            addCriterion("tag not in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagBetween(String value1, String value2) {
            addCriterion("tag between", value1, value2, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotBetween(String value1, String value2) {
            addCriterion("tag not between", value1, value2, "tag");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_name =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user_name >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user_name <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user_name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_name not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("user_name in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("user_name not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserAccountIdIsNull() {
            addCriterion("user_account_id is null");
            return (Criteria) this;
        }

        public Criteria andUserAccountIdIsNotNull() {
            addCriterion("user_account_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserAccountIdEqualTo(String value) {
            addCriterion("user_account_id =", value, "userAccountId");
            return (Criteria) this;
        }

        public Criteria andUserAccountIdNotEqualTo(String value) {
            addCriterion("user_account_id <>", value, "userAccountId");
            return (Criteria) this;
        }

        public Criteria andUserAccountIdGreaterThan(String value) {
            addCriterion("user_account_id >", value, "userAccountId");
            return (Criteria) this;
        }

        public Criteria andUserAccountIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_account_id >=", value, "userAccountId");
            return (Criteria) this;
        }

        public Criteria andUserAccountIdLessThan(String value) {
            addCriterion("user_account_id <", value, "userAccountId");
            return (Criteria) this;
        }

        public Criteria andUserAccountIdLessThanOrEqualTo(String value) {
            addCriterion("user_account_id <=", value, "userAccountId");
            return (Criteria) this;
        }

        public Criteria andUserAccountIdLike(String value) {
            addCriterion("user_account_id like", value, "userAccountId");
            return (Criteria) this;
        }

        public Criteria andUserAccountIdNotLike(String value) {
            addCriterion("user_account_id not like", value, "userAccountId");
            return (Criteria) this;
        }

        public Criteria andUserAccountIdIn(List<String> values) {
            addCriterion("user_account_id in", values, "userAccountId");
            return (Criteria) this;
        }

        public Criteria andUserAccountIdNotIn(List<String> values) {
            addCriterion("user_account_id not in", values, "userAccountId");
            return (Criteria) this;
        }

        public Criteria andUserAccountIdBetween(String value1, String value2) {
            addCriterion("user_account_id between", value1, value2, "userAccountId");
            return (Criteria) this;
        }

        public Criteria andUserAccountIdNotBetween(String value1, String value2) {
            addCriterion("user_account_id not between", value1, value2, "userAccountId");
            return (Criteria) this;
        }

        public Criteria andUserTokenIsNull() {
            addCriterion("user_token is null");
            return (Criteria) this;
        }

        public Criteria andUserTokenIsNotNull() {
            addCriterion("user_token is not null");
            return (Criteria) this;
        }

        public Criteria andUserTokenEqualTo(String value) {
            addCriterion("user_token =", value, "userToken");
            return (Criteria) this;
        }

        public Criteria andUserTokenNotEqualTo(String value) {
            addCriterion("user_token <>", value, "userToken");
            return (Criteria) this;
        }

        public Criteria andUserTokenGreaterThan(String value) {
            addCriterion("user_token >", value, "userToken");
            return (Criteria) this;
        }

        public Criteria andUserTokenGreaterThanOrEqualTo(String value) {
            addCriterion("user_token >=", value, "userToken");
            return (Criteria) this;
        }

        public Criteria andUserTokenLessThan(String value) {
            addCriterion("user_token <", value, "userToken");
            return (Criteria) this;
        }

        public Criteria andUserTokenLessThanOrEqualTo(String value) {
            addCriterion("user_token <=", value, "userToken");
            return (Criteria) this;
        }

        public Criteria andUserTokenLike(String value) {
            addCriterion("user_token like", value, "userToken");
            return (Criteria) this;
        }

        public Criteria andUserTokenNotLike(String value) {
            addCriterion("user_token not like", value, "userToken");
            return (Criteria) this;
        }

        public Criteria andUserTokenIn(List<String> values) {
            addCriterion("user_token in", values, "userToken");
            return (Criteria) this;
        }

        public Criteria andUserTokenNotIn(List<String> values) {
            addCriterion("user_token not in", values, "userToken");
            return (Criteria) this;
        }

        public Criteria andUserTokenBetween(String value1, String value2) {
            addCriterion("user_token between", value1, value2, "userToken");
            return (Criteria) this;
        }

        public Criteria andUserTokenNotBetween(String value1, String value2) {
            addCriterion("user_token not between", value1, value2, "userToken");
            return (Criteria) this;
        }

        public Criteria andUserGmtCreateIsNull() {
            addCriterion("user_gmt_create is null");
            return (Criteria) this;
        }

        public Criteria andUserGmtCreateIsNotNull() {
            addCriterion("user_gmt_create is not null");
            return (Criteria) this;
        }

        public Criteria andUserGmtCreateEqualTo(Long value) {
            addCriterion("user_gmt_create =", value, "userGmtCreate");
            return (Criteria) this;
        }

        public Criteria andUserGmtCreateNotEqualTo(Long value) {
            addCriterion("user_gmt_create <>", value, "userGmtCreate");
            return (Criteria) this;
        }

        public Criteria andUserGmtCreateGreaterThan(Long value) {
            addCriterion("user_gmt_create >", value, "userGmtCreate");
            return (Criteria) this;
        }

        public Criteria andUserGmtCreateGreaterThanOrEqualTo(Long value) {
            addCriterion("user_gmt_create >=", value, "userGmtCreate");
            return (Criteria) this;
        }

        public Criteria andUserGmtCreateLessThan(Long value) {
            addCriterion("user_gmt_create <", value, "userGmtCreate");
            return (Criteria) this;
        }

        public Criteria andUserGmtCreateLessThanOrEqualTo(Long value) {
            addCriterion("user_gmt_create <=", value, "userGmtCreate");
            return (Criteria) this;
        }

        public Criteria andUserGmtCreateIn(List<Long> values) {
            addCriterion("user_gmt_create in", values, "userGmtCreate");
            return (Criteria) this;
        }

        public Criteria andUserGmtCreateNotIn(List<Long> values) {
            addCriterion("user_gmt_create not in", values, "userGmtCreate");
            return (Criteria) this;
        }

        public Criteria andUserGmtCreateBetween(Long value1, Long value2) {
            addCriterion("user_gmt_create between", value1, value2, "userGmtCreate");
            return (Criteria) this;
        }

        public Criteria andUserGmtCreateNotBetween(Long value1, Long value2) {
            addCriterion("user_gmt_create not between", value1, value2, "userGmtCreate");
            return (Criteria) this;
        }

        public Criteria andUserGmtModifiedIsNull() {
            addCriterion("user_gmt_modified is null");
            return (Criteria) this;
        }

        public Criteria andUserGmtModifiedIsNotNull() {
            addCriterion("user_gmt_modified is not null");
            return (Criteria) this;
        }

        public Criteria andUserGmtModifiedEqualTo(Long value) {
            addCriterion("user_gmt_modified =", value, "userGmtModified");
            return (Criteria) this;
        }

        public Criteria andUserGmtModifiedNotEqualTo(Long value) {
            addCriterion("user_gmt_modified <>", value, "userGmtModified");
            return (Criteria) this;
        }

        public Criteria andUserGmtModifiedGreaterThan(Long value) {
            addCriterion("user_gmt_modified >", value, "userGmtModified");
            return (Criteria) this;
        }

        public Criteria andUserGmtModifiedGreaterThanOrEqualTo(Long value) {
            addCriterion("user_gmt_modified >=", value, "userGmtModified");
            return (Criteria) this;
        }

        public Criteria andUserGmtModifiedLessThan(Long value) {
            addCriterion("user_gmt_modified <", value, "userGmtModified");
            return (Criteria) this;
        }

        public Criteria andUserGmtModifiedLessThanOrEqualTo(Long value) {
            addCriterion("user_gmt_modified <=", value, "userGmtModified");
            return (Criteria) this;
        }

        public Criteria andUserGmtModifiedIn(List<Long> values) {
            addCriterion("user_gmt_modified in", values, "userGmtModified");
            return (Criteria) this;
        }

        public Criteria andUserGmtModifiedNotIn(List<Long> values) {
            addCriterion("user_gmt_modified not in", values, "userGmtModified");
            return (Criteria) this;
        }

        public Criteria andUserGmtModifiedBetween(Long value1, Long value2) {
            addCriterion("user_gmt_modified between", value1, value2, "userGmtModified");
            return (Criteria) this;
        }

        public Criteria andUserGmtModifiedNotBetween(Long value1, Long value2) {
            addCriterion("user_gmt_modified not between", value1, value2, "userGmtModified");
            return (Criteria) this;
        }

        public Criteria andUserAvatarUrlIsNull() {
            addCriterion("user_avatar_url is null");
            return (Criteria) this;
        }

        public Criteria andUserAvatarUrlIsNotNull() {
            addCriterion("user_avatar_url is not null");
            return (Criteria) this;
        }

        public Criteria andUserAvatarUrlEqualTo(String value) {
            addCriterion("user_avatar_url =", value, "userAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andUserAvatarUrlNotEqualTo(String value) {
            addCriterion("user_avatar_url <>", value, "userAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andUserAvatarUrlGreaterThan(String value) {
            addCriterion("user_avatar_url >", value, "userAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andUserAvatarUrlGreaterThanOrEqualTo(String value) {
            addCriterion("user_avatar_url >=", value, "userAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andUserAvatarUrlLessThan(String value) {
            addCriterion("user_avatar_url <", value, "userAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andUserAvatarUrlLessThanOrEqualTo(String value) {
            addCriterion("user_avatar_url <=", value, "userAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andUserAvatarUrlLike(String value) {
            addCriterion("user_avatar_url like", value, "userAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andUserAvatarUrlNotLike(String value) {
            addCriterion("user_avatar_url not like", value, "userAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andUserAvatarUrlIn(List<String> values) {
            addCriterion("user_avatar_url in", values, "userAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andUserAvatarUrlNotIn(List<String> values) {
            addCriterion("user_avatar_url not in", values, "userAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andUserAvatarUrlBetween(String value1, String value2) {
            addCriterion("user_avatar_url between", value1, value2, "userAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andUserAvatarUrlNotBetween(String value1, String value2) {
            addCriterion("user_avatar_url not between", value1, value2, "userAvatarUrl");
            return (Criteria) this;
        }
    }

    /**
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}